package com.example.data.networking

import com.cartenz.component_base_network.API_KEY
import com.example.data.database.model.WeatherEntity
import com.example.data.networking.model.WeatherInfoResponse
import com.example.data.networking.model.WeatherResponse
import com.example.domain.data.WeatherInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherForLocation(@Query("q") location: String, @Query("appid") apiKey: String = API_KEY): Response<WeatherInfoResponse>

    @GET("weather")
    suspend fun getWeather(@Query("q") location: String, @Query("appid") apiKey: String = API_KEY): Response<WeatherResponse>

}