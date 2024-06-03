package com.example.kotlinmapweather.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmapweather.R
import com.example.kotlinmapweather.viewmodel.LocationViewModel

class LocationActivity : AppCompatActivity() {
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var latitudeTextView: TextView
    private lateinit var longitudeTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_location)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        locationViewModel = ViewModelProvider(this)[LocationViewModel::class.java]
        locationViewModel.getLocation()

        latitudeTextView = findViewById(R.id.latitudeTextView)
        longitudeTextView = findViewById(R.id.longitudeTextView)

        locationViewModel.latitude.observe(this) {
            latitudeTextView.text = "Latitude: $it"
        }

        locationViewModel.longitude.observe(this) {
            longitudeTextView.text = "Longitude: $it"
        }

    }
}


