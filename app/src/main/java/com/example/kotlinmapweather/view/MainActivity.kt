package com.example.kotlinmapweather.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmapweather.R
import com.example.kotlinmapweather.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById(R.id.textView)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getWeatherData()

        mainViewModel.weatherResponse.observe(this) {
            textView.text = it.main?.temp.toString()
        }

    }

}