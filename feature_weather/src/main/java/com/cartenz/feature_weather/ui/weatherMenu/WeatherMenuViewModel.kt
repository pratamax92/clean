package com.cartenz.feature_weather.ui.weatherMenu

import android.util.Log
import com.cartenz.component_base_domain.onFailure
import com.cartenz.component_base_domain.onSuccess
import com.cartenz.component_base_network.coroutine.CoroutineContextProvider
import com.cartenz.feature_weather.common.DEFAULT_CITY_NAME
import com.example.domain.data.WeatherInfo
import com.example.domain.interaction.weather.GetWeatherUseCase

class WeatherMenuViewModel(private val getWeather: GetWeatherUseCase) :
    com.cartenz.component_base.BaseViewModel<WeatherInfo>() {
    override fun getCoroProvider() = CoroutineContextProvider().main

    fun getWeatherForLocation(location: String = DEFAULT_CITY_NAME) = executeUseCase {
        getWeather.weather(location)
            .onSuccess {
                Log.wtf("Test_","callapi_success")
                getWeatherFromDatabase(location)
            }
            .onFailure {
                Log.wtf("Test_","callapi_failure")
                getWeatherFromDatabase(location)
            }
    }

    //for testing purpose, check data saved to database or not
    fun getWeatherFromDatabase(location: String = DEFAULT_CITY_NAME) = executeUseCase {
        getWeather.weatherOffline(location)
            .onSuccess {
                Log.wtf("Test_","offline_success")
                _viewState.value = com.cartenz.component_base.Success(it)
            }
            .onFailure {
                Log.wtf("Test_","offline_failed")
                _viewState.value = com.cartenz.component_base.Error(it.throwable)
            }
    }
}
