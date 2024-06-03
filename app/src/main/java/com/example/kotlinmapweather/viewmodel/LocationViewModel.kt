package com.example.kotlinmapweather.viewmodel

import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class LocationViewModel(application: Application): AndroidViewModel(application) {
    val locationManager = application.getSystemService(LocationManager::class.java)
    val location = MutableLiveData<Location>()
    val locationError = MutableLiveData<Boolean>()
    val latitude = MutableLiveData<Double>()
    val longitude = MutableLiveData<Double>()

    fun getLocation() {
        if (ContextCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getApplication(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            location.value = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!

            if (location.value != null) {
                latitude.value = location.value!!.latitude
                longitude.value = location.value!!.longitude
                locationError.value = false
            } else {
                locationError.value = true
            }
        }
    }
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            } else {
                locationError.value = true
            }
        }
    }


}