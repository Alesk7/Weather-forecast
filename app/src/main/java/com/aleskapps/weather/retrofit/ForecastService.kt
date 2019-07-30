package com.aleskapps.weather.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("/data/2.5/forecast")
    fun getFiveDaysForecastByCoordinats(@Query("lat") lat: Double,
                                        @Query("lon") lon: Double,
                                        @Query("APPID") key: String)

}