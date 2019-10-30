package com.example.domain.interaction.weather

import com.cartenz.component_base_domain.Result
import com.example.domain.data.WeatherInfo

interface GetWeatherUseCase : com.cartenz.component_base_domain.BaseUseCase<String, WeatherInfo> {

    suspend fun weatherInfo(location: String): Result<WeatherInfo>

    suspend fun weather(location: String): Result<WeatherInfo>


}