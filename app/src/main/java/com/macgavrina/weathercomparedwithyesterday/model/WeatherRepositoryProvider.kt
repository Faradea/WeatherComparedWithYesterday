package com.macgavrina.weathercomparedwithyesterday.model

import com.macgavrina.weathercomparedwithyesterday.services.WeatherApiService

//'object' means it's a sigleton
object WeatherRepositoryProvider{
    fun provideWeatherRepository():WeatherRepository{
        return WeatherRepository(WeatherApiService.create())
    }
}