package com.example.kotlinmapweather

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinmapweather.data.model.weather.WeatherResponse
import com.example.kotlinmapweather.data.network.weather.WeatherRetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getWeather()

    }
    private val disposable = CompositeDisposable()
    private fun getWeather() {
        disposable.add(
            WeatherRetrofitClient().getWeather(41.008469, 28.980261)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherResponse>() {
                        override fun onSuccess(response: WeatherResponse) {
                            println(
                                "Weather: ${response.weather?.get(0)?.main} - ${response.weather?.get(0)?.description}"
                            )
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }
                    }
                )
        )
    }
}