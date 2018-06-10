package com.macgavrina.weathercomparedwithyesterday.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.macgavrina.weathercomparedwithyesterday.R
import com.macgavrina.weathercomparedwithyesterday.model.WeatherItem
import kotlinx.android.synthetic.main.weather_list_item.view.*

class WeatherRecyclerViewAdapter (private val list:MutableList<WeatherItem>) :
        RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder>() {

    private val mItems: MutableList<WeatherItem> = list

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayTemperatureTextView = view.weather_list_item_day_temp_tv
        val nightTemperatureTextView = view.weather_list_item_night_temp_tv
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): WeatherRecyclerViewAdapter.ViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent!!.context)
        // create a new view
        val view = layoutInflater.inflate(R.layout.weather_list_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val item = mItems[position]
        holder.dayTemperatureTextView.text = item.dayTemperature.toString()
        holder.nightTemperatureTextView.text = item.nightTemperature.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = 2 //myDataset.size

}