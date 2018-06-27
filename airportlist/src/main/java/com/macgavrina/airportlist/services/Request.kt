package com.macgavrina.airportlist.services

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded



public interface Request {
    @FormUrlEncoded
    @POST("/demo0013/")
    fun performPostCall(@FieldMap postDataParams: HashMap<String, String>): Call<Any>
}