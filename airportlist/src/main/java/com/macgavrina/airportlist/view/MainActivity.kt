package com.macgavrina.airportlist.view

import android.app.DownloadManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.macgavrina.airportlist.R
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.GsonBuilder
import com.macgavrina.airportlist.data.SumResult
import com.macgavrina.airportlist.services.SumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(p0: View?) {
        val checkIfInputIsNotEmpty:Boolean = (editTextA.text.length != 0) and (editTextB.text.length != 0)
        if (checkIfInputIsNotEmpty) {
            val a:Int = Integer.parseInt(editTextA.text.toString())
            val b:Int = Integer.parseInt(editTextB.text.toString())
            //val result:Int = a + b

            val sumService:SumService = SumService.create()

/*            val postDataParams = HashMap<String, String>()
            postDataParams["a"] = a.toString()
            postDataParams["b"] = b.toString()

            val call = sumService.performPostCall(postDataParams)*/

            val call = sumService.performPostCallWithQuery(a, b)


            call.enqueue(object : Callback<SumResult> {
                override fun onResponse(call: Call<SumResult>, response: Response<SumResult>) {
                    val sumResult:SumResult? = response.body()
                    //val sumResult:SumResult = gson.fromJson(response!!.body(), SumResult::class.java)

                    if (sumResult != null) {
                        val result: Int = sumResult.c
                        textViewResult.text = "Result = ${result}"
                    }
                }

                override fun onFailure(call: Call<SumResult>, t: Throwable) {
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
