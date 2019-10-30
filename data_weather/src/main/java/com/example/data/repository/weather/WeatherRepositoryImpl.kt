package com.example.data.repository.weather

import com.example.data.database.dao.WeatherDao
import com.example.data.database.model.WeatherEntity
import com.example.data.networking.WeatherApi
import com.cartenz.component_base_network.getData
import com.example.domain.data.WeatherInfo
import com.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi,
                            private val weatherDao: WeatherDao) : com.cartenz.component_base_network.BaseRepository<WeatherInfo, WeatherEntity>(), WeatherRepository {
  override suspend fun getWeatherForLocation(location: String): com.cartenz.component_base_domain.Result<WeatherInfo> {
    return fetchData(
      apiDataProvider = {
        weatherApi.getWeatherForLocation(location).getData(
          cacheAction = { weatherDao.saveWeatherInfo(it) }, //save if success
          fetchFromCacheAction = { weatherDao.getWeatherInfoForCity(location) } //if failed, call db
          )
      },
      dbDataProvider = { weatherDao.getWeatherInfoForCity(location) }
    )
  }

  override suspend fun getWeather(location: String): com.cartenz.component_base_domain.Result<WeatherInfo> {
    return fetchData(
      apiDataProvider = {
        weatherApi.getWeather(location).getData()
      }
    )
  }
}