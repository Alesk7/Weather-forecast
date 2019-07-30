package com.aleskapps.weather.entities

class ForecastResponse (
    val cod: String,
    val message: String,
    val cnt: Long,
    val list: List<Forecast>
)