package com.macgavrina.airportlist.services

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.macgavrina.airportlist.data.SumResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


const val server:String = "http://demo.harrix.org"

interface SumService {
    @FormUrlEncoded
    @POST("/demo0013/")
    //fun performPostCall(@FieldMap postDataParams: HashMap<String, String>): Call<SumResult>
    fun performPostCallWithQuery(@Field("a") a:Int, @Field("b") b:Int): Call<SumResult>

    companion object ApiFactory{
        fun create():SumService{

            val gson = GsonBuilder().create()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(server)
                    .build()
            return retrofit.create(SumService::class.java)
        }

    }
}