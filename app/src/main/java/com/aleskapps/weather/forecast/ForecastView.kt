package com.aleskapps.weather.forecast

import com.arellomobile.mvp.MvpView

interface ForecastView: MvpView {
    fun setForecast(forecast: List<ForecastViewModel>)
    fun showLoadingView()
    fun hideLoadingView()
    fun showToastMessage(message: String)
}