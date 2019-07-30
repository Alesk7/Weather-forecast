package com.aleskapps.weather.forecast

import android.os.Bundle
import com.aleskapps.weather.R
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter

class ForecastActivity : MvpAppCompatActivity(), ForecastView {

    @InjectPresenter
    lateinit var presenter: ForecastPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
    }

}
