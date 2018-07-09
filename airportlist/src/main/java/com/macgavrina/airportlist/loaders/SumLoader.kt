package com.macgavrina.airportlist.loaders

import android.content.Context
import android.content.Loader
import android.database.Cursor
import android.text.method.TextKeyListener.clear
import android.util.Log
import com.macgavrina.airportlist.data.Airport
import com.macgavrina.airportlist.data.SumResult
import com.macgavrina.airportlist.database.SumTable
import com.macgavrina.airportlist.services.SumService
import com.macgavrina.airportlist.services.SumService.ApiFactory
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController.getContext


class SumLoader(context: Context, val a: Int, val b:Int) : Loader<Cursor>(context) {

    private var sumService: SumService = SumService.create()

    override fun onForceLoad() {
        Log.d("SumApp", "onForceLoad")

        val call = sumService.performPostCallWithQuery(a, b)


        call.enqueue(object : Callback<SumResult> {
            override fun onResponse(call: Call<SumResult>, response: Response<SumResult>) {
                Log.d("SumApp", "onResponse")
                val sumResult:SumResult? = response.body()
                //val sumResult:SumResult = gson.fromJson(response!!.body(), SumResult::class.java)

                if (sumResult != null) {
                    val result: Int = sumResult.c
                    val sumResult: SumResult = SumResult(a, b, result)
                    val sumTable: SumTable = SumTable(context)
                    sumTable.clear()
                    sumTable.save(sumResult)

                    val cursor:Cursor = context.contentResolver.query(sumTable.URI, null, null, null, null)
                    deliverResult(cursor)
                }
            }

            override fun onFailure(call: Call<SumResult>, t: Throwable) {
                deliverResult(null)
            }
        })

    }
}