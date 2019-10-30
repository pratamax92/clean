package com.example.data.repository.weather

import com.cartenz.component_base_domain.Result
import com.cartenz.component_base_network.DB_ENTRY_ERROR
import com.cartenz.component_base_network.coroutine.CoroutineContextProvider
import com.cartenz.component_base_network.getData
import com.example.data.database.dao.WeatherDao
import com.example.data.database.model.WeatherEntity
import com.example.data.networking.WeatherApi
import com.example.domain.data.WeatherInfo
import com.example.domain.repository.WeatherRepository
import kotlinx.coroutines.withContext
import org.koin.core.inject

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
) : com.cartenz.component_base_network.BaseRepository<WeatherInfo>(),
    WeatherRepository {

    private val weatherDao: WeatherDao by inject()
    private val contextProvider: CoroutineContextProvider by inject()


    override suspend fun getWeather(location: String): com.cartenz.component_base_domain.Result<WeatherInfo> {
        val data = fetchData(
            apiDataProvider = {
                weatherApi.getWeather(location).getData(
                    logic = { it ->
                        val entity = WeatherEntity(it.id, it.weather, it.main, it.name)
                        weatherDao.saveWeatherInfo(entity)
                    }
                )
            }
        )
        return data
    }


    override suspend fun getWeatherOffline(location: String): Result<WeatherInfo> {
        val entity = weatherDao.getWeatherInfoForCity(location)
        return if (entity != null) {
            withContext(contextProvider.io) {
                val weatherInfo = WeatherInfo(
                    entity!!.weather,
                    entity.main?.temp ?: 0.0,
                    entity.main?.humidity ?: 0,
                    entity.main?.pressure ?: 0.0
                )
                com.cartenz.component_base_domain.Success(weatherInfo)
            }
        } else {
            withContext(contextProvider.io) {
                com.cartenz.component_base_domain.Failure(
                    com.cartenz.component_base_domain.HttpError(Throwable(DB_ENTRY_ERROR))
                )
            }
        }
    }


}