package com.aleskapps.weather.map

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.aleskapps.weather.R
import com.aleskapps.weather.databinding.ActivityMapBinding
import com.aleskapps.weather.forecast.ForecastActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : MvpAppCompatActivity(), MapView, OnMapReadyCallback {

    @InjectPresenter
    lateinit var presenter: MapPresenter
    lateinit var binding: ActivityMapBinding
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        binding.presenter = presenter
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnCameraMoveStartedListener { showForecastButton.visibility = View.GONE }
        mMap.setOnCameraIdleListener { showForecastButton.visibility = View.VISIBLE }
    }

    override fun showForecastView() {
        val intent = Intent(this, ForecastActivity::class.java).also {
            it.putExtra(getString(R.string.lat_extra), mMap.cameraPosition.target.latitude)
            it.putExtra(getString(R.string.lon_extra), mMap.cameraPosition.target.longitude)
        }
        startActivity(intent)
    }
}
