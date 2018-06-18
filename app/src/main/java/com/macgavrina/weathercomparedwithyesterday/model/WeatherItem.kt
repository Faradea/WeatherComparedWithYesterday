package com.macgavrina.weathercomparedwithyesterday.model

import com.google.gson.annotations.SerializedName

data class WeatherItem(
        val dateTime:Long,
        val updateDateTime:Long,
        val dayTemperature:Int,
        val nightTemperature:Int
)