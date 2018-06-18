package com.macgavrina.weathercomparedwithyesterday.services

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.macgavrina.weathercomparedwithyesterday.model.WeatherItemFromAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WeatherApiService {

    @GET("forecast/d6f73b29bc0464cabf7b8cc979e019aa/37.8267,-122.4233")
    fun getWeather():io.reactivex.Observable<List<WeatherItemFromAPI>>

    companion object Factory{
        fun create():WeatherApiService{
            val gson: Gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://api.darksky.net/")
                    .build()
            return retrofit.create(WeatherApiService::class.java)
        }
    }
}