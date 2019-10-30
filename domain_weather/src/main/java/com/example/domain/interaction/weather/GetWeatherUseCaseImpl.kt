package com.example.domain.interaction.weather

import com.cartenz.component_base_domain.Result
import com.example.domain.data.WeatherInfo
import com.example.domain.repository.WeatherRepository

class GetWeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : GetWeatherUseCase {
    override suspend fun weatherOffline(location: String): Result<WeatherInfo> {
        return weatherRepository.getWeatherOffline(location)
    }

    override suspend fun weather(location: String): Result<WeatherInfo> {
        return weatherRepository.getWeather(location)
    }

}