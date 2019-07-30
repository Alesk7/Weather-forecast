package com.aleskapps.weather.map

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.aleskapps.weather.R
import com.aleskapps.weather.databinding.ActivityMapBinding
import com.aleskapps.weather.entities.Place
import com.aleskapps.weather.forecast.ForecastActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_map.*
import java.util.*

class MapActivity : MvpAppCompatActivity(), MapView, OnMapReadyCallback {

    @InjectPresenter
    lateinit var presenter: MapPresenter
    lateinit var binding: ActivityMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.title_activity_maps)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        binding.presenter = presenter
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnCameraMoveStartedListener { showForecastButton.visibility = View.GONE }
        map.setOnCameraIdleListener { showForecastButton.visibility = View.VISIBLE }
    }

    override fun showForecastView() {
        val intent = Intent(this, ForecastActivity::class.java).also {
            it.putExtra(getString(R.string.place_extra), getCurrentPlace())
        }
        startActivity(intent)
    }

    private fun getCurrentPlace(): Place {
        val latitude = map.cameraPosition.target.latitude
        val longitude = map.cameraPosition.target.longitude
        return Place(latitude, longitude, getCurrentAddress(latitude, longitude))
    }

    private fun getCurrentAddress(latitude: Double, longitude: Double): String {
        val geoCoder = Geocoder(this, Locale.getDefault())
        val addresses = geoCoder.getFromLocation(latitude, longitude, 1)
        if (addresses != null && addresses.size != 0) {
            return addresses[0].getAddressLine(0)
        }
        return ""
    }

}
