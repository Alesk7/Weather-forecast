package com.aleskapps.weather.retrofit

import com.aleskapps.weather.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitClient: Retrofit? = null

fun getForecastService(): ForecastService {
    return getClient(BuildConfig.BASE_API_URL).create(ForecastService::class.java)
}

private fun getClient(baseUrl: String): Retrofit {
    if(retrofitClient == null) {
        retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(getGSONClient()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }
    return retrofitClient!!
}

private fun getGSONClient(): Gson {
    return GsonBuilder()
        .setLenient()
        .serializeNulls()
        .create()
}