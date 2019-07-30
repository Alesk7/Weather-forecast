package com.aleskapps.weather.entities

import com.google.gson.annotations.SerializedName

data class Forecast (
    @SerializedName("dt")
    val dataForecasted: Long,
    @SerializedName("main")
    val weather: Weather,
    @SerializedName("weather")
    val weatherDescription: List<WeatherDescription>,
    val clouds: Clouds,
    val wind: Wind,
    @SerializedName("dt_txt")
    val dataForecastedText: String
)