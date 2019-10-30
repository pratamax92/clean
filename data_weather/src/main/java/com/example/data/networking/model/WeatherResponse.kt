package com.example.data.networking.model

import com.cartenz.component_base_network.DomainMapper
import com.example.domain.data.WeatherInfo

data class WeatherResponse(
    val id: Int? = 0,
    val weather: List<Weather>?,
    val main: MainInfo?,
    val name: String? = ""
) : DomainMapper<WeatherInfo> {

    override fun mapToDomainModel() = WeatherInfo(weather, main?.temp ?: 0.0, main?.humidity ?: 0, main?.pressure ?: 0.0)
}


