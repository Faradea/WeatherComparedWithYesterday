package com.macgavrina.weathercomparedwithyesterday.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.macgavrina.weathercomparedwithyesterday.R
import com.macgavrina.weathercomparedwithyesterday.adapters.WeatherRecyclerViewAdapter
import com.macgavrina.weathercomparedwithyesterday.model.WeatherItem
import kotlinx.android.synthetic.main.fragment_weather_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

const val LOG_TAG:String = "WeatherApp"

/**
 * A simple [Fragment] subclass.
 *
 */
class WeatherListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        //setup RecyclerView


/*        viewAdapter = WeatherRecyclerViewAdapter(myDataset)*/

        val weatherItem1:WeatherItem = WeatherItem(100000, 1000001, 22, 13)
        val weatherItem2:WeatherItem = WeatherItem(2222222, 2222222, 55, 40)
        val list:MutableList<WeatherItem> = mutableListOf(weatherItem1, weatherItem2)

        weather_recycler_view.adapter = WeatherRecyclerViewAdapter(list)
        weather_recycler_view.layoutManager = LinearLayoutManager(context)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //textView2.text = "New text"
        //recyclerView = activity!!.findViewById(R.id.weather_recycler_view)
        //Log.d(LOG_TAG, "recyclerView = ${recyclerView}")
        //recyclerView.adapter = WeatherRecyclerViewAdapter(list)

    }
}
