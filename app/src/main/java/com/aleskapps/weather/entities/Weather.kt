package com.aleskapps.weather.entities

import com.google.gson.annotations.SerializedName

data class Weather (
    val temp: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Double,
    @SerializedName("sea_level")
    val seaLevel: Double,
    @SerializedName("grnd_level")
    val groundLevel: Double,
    val humidity: Int
)