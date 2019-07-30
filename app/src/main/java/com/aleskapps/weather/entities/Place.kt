package com.aleskapps.weather.entities

import java.io.Serializable

data class Place (
    val latitude: Double,
    val lonngitude: Double,
    val address: String
): Serializable