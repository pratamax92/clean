package com.example.domain.interaction.weather

import com.cartenz.component_base_domain.Result
import com.example.domain.data.WeatherInfo

interface GetWeatherUseCase : com.cartenz.component_base_domain.BaseUseCase<String, WeatherInfo> {

    suspend fun weather(location: String): Result<WeatherInfo>
    suspend fun weatherOffline(location: String): Result<WeatherInfo>



    //add here if you want to create new call API function


}