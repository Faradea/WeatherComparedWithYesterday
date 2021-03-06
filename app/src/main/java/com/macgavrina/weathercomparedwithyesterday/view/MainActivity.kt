package com.macgavrina.weathercomparedwithyesterday.view

import android.app.LoaderManager
import android.content.Loader
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.macgavrina.weathercomparedwithyesterday.R
import com.macgavrina.weathercomparedwithyesterday.R.id.*
import com.macgavrina.weathercomparedwithyesterday.adapters.WeatherRecyclerViewAdapter
import com.macgavrina.weathercomparedwithyesterday.fragments.WeatherListFragment
import com.macgavrina.weathercomparedwithyesterday.loaders.WeatherLoader
import com.macgavrina.weathercomparedwithyesterday.model.WeatherItem
import com.macgavrina.weathercomparedwithyesterday.model.WeatherItemFromAPI
import com.macgavrina.weathercomparedwithyesterday.model.WeatherRepository
import com.macgavrina.weathercomparedwithyesterday.model.WeatherRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_weather_list.*

const val LOG_TAG:String = "WeatherApp"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<MutableList<WeatherItem>?> {

    var list:MutableList<WeatherItem>? = null
    val listRX:MutableList<WeatherItemFromAPI> = mutableListOf()
    val compositeDisposable:CompositeDisposable = CompositeDisposable()

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<MutableList<WeatherItem>?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadFinished(p0: Loader<MutableList<WeatherItem>?>?, p1: MutableList<WeatherItem>?) {
        list = p1
        Log.d(LOG_TAG, "onLoadFinished")
    }

    override fun onLoaderReset(p0: Loader<MutableList<WeatherItem>?>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //lateinit var contentMainMainTV:TextView
    lateinit var weatherListFragment:WeatherListFragment
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val weatherItem:WeatherItem = WeatherItem(100, 101, 20, 15)
            Log.d("WeatherCompared", "dateTime = ${weatherItem.dateTime}, dayTemperature = ${weatherItem.dayTemperature}")

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        //ToDo REFACT вынести код во фрагмент
/*        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_for_fragment, WeatherListFragment())
                .commit()*/
        //contentMainMainTV = findViewById(R.id.content_main_mainTV)

        //ToDo NEW сделать загрузку данных через Loader (в коде ниже нужно его инициализировать)
        //ToDo REFACT учесть пересоздание активити при повороте экрана в Loader
        //ToDo NEW сделать swipeToRefresh
/*        val weatherLoaderCallbacks:LoaderManager.LoaderCallbacks<MutableList<WeatherItem>?> = WeatherLoader
        supportLoaderManager.initLoader(1, Bundle.EMPTY, weatherLoaderCallbacks)*/

        list = prepareDataForList()
        viewManager = LinearLayoutManager(this)

        weather_recycler_view.adapter = WeatherRecyclerViewAdapter(list)
        weather_recycler_view.layoutManager = viewManager

        getWeatherByAPI()

        //ToDo REFACT сделать обновление списка через notifyDataSetChanged
/*      list = prepareDataForList()
        Log.d(LOG_TAG, "Notify dataset changed")
        weather_recycler_view.adapter.notifyDataSetChanged()*/
    }

    private fun getWeatherByAPI() {
        val weatherItemFromAPI:WeatherItemFromAPI = WeatherItemFromAPI("37.8267","-122.4233")
        val weatherRepository:WeatherRepository = WeatherRepositoryProvider.provideWeatherRepository()
        weatherRepository.getWeather()
    }

    fun prepareDataForList():MutableList<WeatherItem> {
        val weatherItem1:WeatherItem = WeatherItem(100000, 1000001, 22, 13)
        val weatherItem2:WeatherItem = WeatherItem(2222222, 2222222, 55, 40)
        val weatherItem3:WeatherItem = WeatherItem(2222222, 2222222, 55, 40)
        return mutableListOf(weatherItem1, weatherItem2, weatherItem3)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                //contentMainMainTV.text = "Camera tab"
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
