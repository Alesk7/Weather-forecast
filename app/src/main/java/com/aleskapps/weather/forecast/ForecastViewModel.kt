package com.aleskapps.weather.forecast

data class ForecastViewModel (
    val date: String = "",
    val weather: String = "",
    val temp: String = "",
    val maxTemp: String = "",
    val minTemp: String = "",
    val wind: String = "",
    val pressure: String = "",
    val humidity: String = "",
    val clouds: String = "",
    val iconUrl: String = ""
)