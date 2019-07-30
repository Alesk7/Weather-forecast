package com.aleskapps.weather.forecast

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.aleskapps.weather.R
import com.aleskapps.weather.databinding.ActivityForecastBinding
import com.aleskapps.weather.entities.Place
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_forecast.*

class ForecastActivity : MvpAppCompatActivity(), ForecastView {

    @InjectPresenter
    lateinit var presenter: ForecastPresenter
    lateinit var forecastAdapter: ForecastAdapter
    lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.title_activity_forecast)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forecast)
        forecastAdapter = ForecastAdapter(this)
        presenter.onCreate(intent.getSerializableExtra(getString(R.string.place_extra)) as Place, getString(R.string.openweather_API_KEY))
    }

    override fun onStart() {
        super.onStart()
        binding.adapter = forecastAdapter
        binding.place = presenter.place
    }

    override fun showLoadingView() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingView() {
        progressBar.visibility = View.GONE
    }

    override fun setForecast(forecast: List<ForecastViewModel>) {
        forecastAdapter.items = forecast
        forecastAdapter.notifyDataSetChanged()
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
