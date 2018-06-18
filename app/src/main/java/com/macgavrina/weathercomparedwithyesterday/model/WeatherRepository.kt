package com.macgavrina.weathercomparedwithyesterday.model

import com.macgavrina.weathercomparedwithyesterday.services.WeatherApiService

class WeatherRepository(val apiService: WeatherApiService){

    fun getWeather():io.reactivex.Observable<List<WeatherItemFromAPI>>{
        return apiService.getWeather()
    }
}