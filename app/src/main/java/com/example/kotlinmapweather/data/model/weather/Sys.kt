package com.example.kotlinmapweather.data.model.weather

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("type")
    val type: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("message")
    val country: String?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?
)
