package com.cartenz.feature_weather.ui.weatherMenu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cartenz.component_base.BaseViewModel
import com.cartenz.component_base.Loading
import com.cartenz.component_base.ViewState
import com.cartenz.component_base_domain.onFailure
import com.cartenz.component_base_domain.onSuccess
import com.cartenz.component_base_network.coroutine.CoroutineContextProvider
import com.cartenz.feature_weather.common.DEFAULT_CITY_NAME
import com.example.domain.data.WeatherInfo
import com.example.domain.interaction.weather.GetWeatherUseCase


class WeatherMenuViewModel(private val getWeather: GetWeatherUseCase) : BaseViewModel() {

    override fun getCoroProvider() = CoroutineContextProvider().main

    protected val _weatherState = MutableLiveData<ViewState<WeatherInfo>>()
    val weatherState: LiveData<ViewState<WeatherInfo>>
        get() = _weatherState

    protected val _checkDbExist = MutableLiveData<Boolean>()
    val checkDbExist: LiveData<Boolean>
        get() = _checkDbExist


    fun getWeatherForLocation(location: String = DEFAULT_CITY_NAME) =
        executeUseCase {
            _weatherState.value = Loading()
            getWeather.weather(location)
                .onSuccess {
                    _weatherState.value = com.cartenz.component_base.Success(it)
                }
                .onFailure {
                    _weatherState.value = com.cartenz.component_base.Error(it.throwable)
                }
        }

    fun getWeatherFromDatabase(location: String = DEFAULT_CITY_NAME) = executeUseCase {
        getWeather.weatherOffline(location)
            .onSuccess {
                _checkDbExist.value = true
            }
            .onFailure {
                _checkDbExist.value = false
            }
    }
}