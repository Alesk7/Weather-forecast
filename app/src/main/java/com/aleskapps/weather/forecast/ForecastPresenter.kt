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

    private lateinit var forecastRepository: ForecastRepository
    lateinit var place: Place

    @SuppressLint("CheckResult")
    fun onCreate(place: Place, apiKey: String) {
        if(!isInRestoreState(viewState)) {
            this.place = place
            forecastRepository = ForecastRepository(apiKey)
            forecastRepository.getFiveDaysForecastByCoordinats(place.latitude, place.longitude)
                .doOnSubscribe { viewState.showLoadingView() }
                .doFinally { viewState.hideLoadingView() }
                .subscribe({ viewState.setForecast(transformForecast(it)) },
                    { viewState.showToastMessage(it.localizedMessage) })
        }
    }

    private fun transformForecast(forecast: List<Forecast>): List<ForecastViewModel> {
        val forecastList = ArrayList<ForecastViewModel>()
        var day = -1
        forecast.forEach {
            val date = Date(it.dataForecasted * 1000)
            if(date.day > day) {
                day = date.day
                forecastList.add(
                    ForecastViewModel(
                        SimpleDateFormat("d MMMM yyyy").format(date),
                        String.format("%s, %s", it.weatherDescription[0].main, it.weatherDescription[0].description),
                        it.weather.temp.toInt().toString(),
                        it.weather.tempMax.toInt().toString(),
                        it.weather.tempMin.toInt().toString(),
                        it.wind.speed.toString(),
                        it.weather.pressure.toString(),
                        it.weather.humidity.toString(),
                        it.clouds.all.toString(),
                        String.format("http://openweathermap.org/img/wn/%s@2x.png", it.weatherDescription[0].icon)
                    )
                )
            }
        }
        return forecastList
    }

}