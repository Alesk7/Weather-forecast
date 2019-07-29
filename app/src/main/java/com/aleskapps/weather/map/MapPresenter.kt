package com.aleskapps.weather.map

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MapPresenter: MvpPresenter<MapView>() {

    fun onShowForecastClicked() {
        viewState.showForecastView()
    }

}