package com.macgavrina.airportlist.data

import com.google.gson.annotations.SerializedName

data class SumResult (
        @SerializedName("a") val a:Int,
        @SerializedName("b") val b:Int,
        @SerializedName("c") val c:Int
)