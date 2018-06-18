package com.macgavrina.weathercomparedwithyesterday.model

import com.google.gson.annotations.SerializedName

data class WeatherItemFromAPI(
        @SerializedName("latitude") val latitude:String,
        @SerializedName("longitude") val longitude:String
)