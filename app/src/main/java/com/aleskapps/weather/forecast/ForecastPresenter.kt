package com.aleskapps.weather.forecast

import android.annotation.SuppressLint
import com.aleskapps.weather.entities.Forecast
import com.aleskapps.weather.entities.Place
import com.aleskapps.weather.repository.ForecastRepository
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@InjectViewState
class ForecastPresenter: MvpPresenter<ForecastView>() {

    private val forecastRepository = ForecastRepository("8c6872db382cf7e3ab5016108959e627")
    lateinit var place: Place

    @SuppressLint("CheckResult")
    fun onCreate(place: Place) {
        if(!isInRestoreState(viewState)) {
            this.place = place
            forecastRepository.getFiveDaysForecastByCoordinats(place.latitude, place.longitude)
                .doOnSubscribe { viewState.showLoadingView() }
                .doFinally { viewState.hideLoadingView() }
                .subscribe({ viewState.setForecast(transformForecast(it)) },
                    { viewState.showToastMessage(it.localizedMessage) })
        }
    }

    private fun transformForecast(forecast: List<Forecast>): List<ForecastViewModel> {
        val forecastList = ArrayList<ForecastViewModel>()
        forecast.forEach {
            forecastList.add(ForecastViewModel(
                SimpleDateFormat("d MMMM yyyy").format(Date(it.dataForecasted)),
                String.format("%s, %s", it.weatherDescription[0].main, it.weatherDescription[0].description),
                it.weather.temp.toInt().toString(),
                it.weather.tempMax.toInt().toString(),
                it.weather.tempMin.toInt().toString(),
                it.wind.speed.toString(),
                it.weather.pressure.toString(),
                it.weather.humidity.toString(),
                it.clouds.all.toString(),
                it.weather.temp.toString()
            ))
        }
        return forecastList
    }

}