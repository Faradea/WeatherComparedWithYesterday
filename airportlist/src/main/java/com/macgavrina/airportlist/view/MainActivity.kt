package com.macgavrina.airportlist.view

import android.app.DownloadManager
import android.app.LoaderManager
import android.content.Loader
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.macgavrina.airportlist.R
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.GsonBuilder
import com.macgavrina.airportlist.data.SumResult
import com.macgavrina.airportlist.database.SumTable
import com.macgavrina.airportlist.loaders.SumLoader
import com.macgavrina.airportlist.services.SumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class MainActivity : AppCompatActivity(), View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    override fun onCreateLoader(id: Int, p1: Bundle?): Loader<Cursor> {
        Log.d("SumApp", "onCreateLoader")
        return SumLoader(this, 0, 0)

    }

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        Log.d("SumApp", "onLoadFinished")
        if (data != null) {
            val sumTable: SumTable = SumTable(this)
            val sumResult: SumResult = sumTable.fromCursor(data)
            val result: Int = sumResult.c
            textViewResult.text = "Result = ${result}"

            loaderManager.destroyLoader(101)
        }
    }

    override fun onLoaderReset(p0: Loader<Cursor>?) {
    }

    override fun onClick(p0: View?) {
        val checkIfInputIsNotEmpty:Boolean = (editTextA.text.length != 0) and (editTextB.text.length != 0)
        if (checkIfInputIsNotEmpty) {
            val a:Int = Integer.parseInt(editTextA.text.toString())
            val b:Int = Integer.parseInt(editTextB.text.toString())
            loaderManager.restartLoader(101, Bundle.EMPTY, this)

/*            val sumService:SumService = SumService.create()

*//*            val postDataParams = HashMap<String, String>()
            postDataParams["a"] = a.toString()
            postDataParams["b"] = b.toString()

            val call = sumService.performPostCall(postDataParams)*//*

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
            })*/


        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSumm.setOnClickListener(this)

        loaderManager.initLoader(101, Bundle.EMPTY, this)
    }
}
