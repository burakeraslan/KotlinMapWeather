package com.example.kotlinmapweather.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kotlinmapweather.model.WeatherResponse
import com.example.kotlinmapweather.service.WeatherRetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application): AndroidViewModel(application) {
    val weatherResponse = MutableLiveData<WeatherResponse>()
    val weatherResponseError = MutableLiveData<Boolean>()
    val weatherResponseLoading = MutableLiveData<Boolean>()

    private val disposable = CompositeDisposable()

    fun getWeatherData() {
        weatherResponseLoading.value = true
        disposable.add(
            WeatherRetrofitClient().getWeather(41.008469, 28.980261)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherResponse>() {
                    override fun onSuccess(response: WeatherResponse) {
                        weatherResponse.value = response
                        weatherResponseLoading.value = false
                    }
                    override fun onError(e: Throwable) {
                        weatherResponseError.value = true
                        weatherResponseLoading.value = false
                    }
                }
                )
        )
    }
}