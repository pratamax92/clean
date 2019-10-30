package com.example.domain.repository

import com.example.domain.data.WeatherInfo

interface WeatherRepository {
    suspend fun getWeather(location: String): com.cartenz.component_base_domain.Result<WeatherInfo>
    suspend fun getWeatherOffline(location: String): com.cartenz.component_base_domain.Result<WeatherInfo>
}