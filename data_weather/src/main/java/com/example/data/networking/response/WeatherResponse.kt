package com.example.data.networking.response

import com.cartenz.component_base_network.DomainMapper
import com.cartenz.component_base_network.RoomMapper
import com.example.data.database.model.WeatherEntity
import com.example.domain.data.WeatherInfo
import org.koin.core.KoinComponent


class WeatherResponse(
    val id: Int? = 0,
    val weather: List<Weather>?,
    val main: MainInfo?,
    val name: String? = ""
) : DomainMapper<WeatherInfo>, KoinComponent {
    override fun mapToDomainModel() = WeatherInfo(weather, main?.temp ?: 0.0, main?.humidity ?: 0, main?.pressure ?: 0.0)
}


data class MainInfo(
    val temp: Double? = 0.0,
    val pressure: Double? = 0.0,
    val humidity: Int? = 0
)

data class Weather(
    val id: Int? = 0,
    val main: String? = "",
    val description: String? = "",
    val icon: String? = ""
)



