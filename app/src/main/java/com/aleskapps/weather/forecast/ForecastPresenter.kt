package com.aleskapps.weather.forecast

import com.aleskapps.weather.entities.Place
import com.aleskapps.weather.repository.ForecastRepository
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class ForecastPresenter: MvpPresenter<ForecastView>() {

    private val forecastRepository = ForecastRepository("8c6872db382cf7e3ab5016108959e627")
    lateinit var place: Place

    fun onCreate(place: Place) {
        if(!isInRestoreState(viewState)) {
            this.place = place
            forecastRepository.getFiveDaysForecastByCoordinats(place.latitude, place.longitude)
                .doOnSubscribe { viewState.showLoadingView() }
                .doFinally { viewState.hideLoadingView() }
                .subscribe()
        }
    }

}