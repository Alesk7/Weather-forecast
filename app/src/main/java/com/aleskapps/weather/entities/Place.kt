package com.aleskapps.weather.entities

import java.io.Serializable

data class Place (
    val latitude: Double,
    val longitude: Double,
    val address: String
): Serializable