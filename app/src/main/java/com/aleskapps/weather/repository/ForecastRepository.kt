package com.aleskapps.weather.repository

import com.aleskapps.weather.entities.Forecast
import com.aleskapps.weather.retrofit.getForecastService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForecastRepository (private val apiKey: String) {

    private val forecastService = getForecastService()

    fun getFiveDaysForecastByCoordinats(latitude: Double, longitude: Double): Single<List<Forecast>> {
        return forecastService.getFiveDaysForecastByCoordinats(latitude, longitude, "metric", apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.list }
    }

}