package com.macgavrina.airportlist.view

import android.app.DownloadManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.macgavrina.airportlist.R
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.macgavrina.airportlist.services.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val server:String = "http://demo.harrix.org"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(p0: View?) {
        val checkIfInputIsNotEmpty:Boolean = (editTextA.text.length != 0) and (editTextB.text.length != 0)
        if (checkIfInputIsNotEmpty) {
            val a:Int = Integer.parseInt(editTextA.text.toString())
            val b:Int = Integer.parseInt(editTextB.text.toString())
            //val result:Int = a + b


            val postDataParams = HashMap<String, String>()
            postDataParams["a"] = a.toString()
            postDataParams["b"] = b.toString()

            val gson = GsonBuilder().create()

            val retrofit:Retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(server)
                    .build()

            val request: Request = retrofit.create(Request::class.java)

            val call = request.performPostCall(postDataParams)


            call.enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    val map = gson.fromJson(response.body()!!.toString(), HashMap::class.java)

                    val result:String = map["c"].toString()
                    textViewResult.text = "Result = ${result}"
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    textViewResult.text = "Server error"
                }
            })


        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSumm.setOnClickListener(this)
    }
}
