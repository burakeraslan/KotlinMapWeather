package com.example.kotlinmapweather.service

import com.example.kotlinmapweather.model.WeatherResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRetrofitClient {
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        private const val API_KEY = "3178187dfaf03f85f27e52123775161c"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    fun getWeather(lat: Double, lon: Double): Single<WeatherResponse> {
        return retrofit.getWeather(lat, lon, API_KEY)
    }
}
