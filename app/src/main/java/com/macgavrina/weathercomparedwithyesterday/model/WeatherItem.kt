package com.macgavrina.weathercomparedwithyesterday.model

data class WeatherItem(val dateTime:Long, val updateDateTime:Long, val dayTemperature:Int, val nightTemperature:Int) {
}