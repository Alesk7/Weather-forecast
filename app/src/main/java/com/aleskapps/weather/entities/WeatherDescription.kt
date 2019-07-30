package com.aleskapps.weather.entities

data class WeatherDescription(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)