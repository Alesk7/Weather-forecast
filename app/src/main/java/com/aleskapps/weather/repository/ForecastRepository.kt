package com.aleskapps.weather.repository

import com.aleskapps.weather.entities.Forecast
import io.reactivex.Single

interface ForecastRepository {
    fun getFiveDaysForecastByCoordinats(latitude: Double, longitude: Double): Single<List<Forecast>>
}