package com.macgavrina.weathercomparedwithyesterday.loaders

import android.content.AsyncTaskLoader
import android.content.Context
import android.os.SystemClock
import com.macgavrina.weathercomparedwithyesterday.model.City
import com.macgavrina.weathercomparedwithyesterday.model.WeatherItem

public class WeatherLoader(context: Context?) : AsyncTaskLoader<MutableList<WeatherItem>?>(context) {

    override fun loadInBackground(): MutableList<WeatherItem>? {
        SystemClock.sleep(2000);
        val weatherItem1:WeatherItem = WeatherItem(100000, 1000001, 22, 13)
        val weatherItem2:WeatherItem = WeatherItem(2222222, 2222222, 55, 40)
        val weatherItem3:WeatherItem = WeatherItem(2222222, 2222222, 55, 40)
        val list:MutableList<WeatherItem>? = mutableListOf(weatherItem1, weatherItem2, weatherItem3)
        return list
    }

}