package com.macgavrina.airportlist.data

import com.google.gson.annotations.SerializedName

data class Airport(

        @SerializedName("iata") val mIata:String,
        @SerializedName("name") val mName:String,
        @SerializedName("airport_name") val mAirportName:String
)