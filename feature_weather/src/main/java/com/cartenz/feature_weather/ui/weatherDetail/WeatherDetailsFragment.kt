package com.cartenz.feature_weather.ui.weatherDetail

import android.util.Log
import com.cartenz.feature_weather.R

class WeatherDetailsFragment : com.cartenz.component_base.BaseFragment() {
    override fun getLayout() = R.layout.fragment_weather_detail

    override fun viewReady() {
        val var1 = arguments!!.getString("var1")
        val var2 = arguments!!.getString("var2")
        Log.wtf("Test_var", "" + var1 + " - " + var2)

    }

    companion object {
        fun newInstance() = WeatherDetailsFragment()
    }
}