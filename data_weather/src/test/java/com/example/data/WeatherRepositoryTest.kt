package com.example.data

import com.cartenz.component_base_network.utils.Connectivity
import com.example.data.database.dao.WeatherDao
import com.example.data.networking.WeatherApi
import com.example.data.repository.weather.WeatherRepositoryImpl
import com.example.data.utils.*
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class WeatherRepositoryTest {
  
  private val weatherTestApi: WeatherApi = mock()
  private val weatherDao: WeatherDao = mock()
  private val connectivity: Connectivity = mock()
  private val weatherRepository = WeatherRepositoryImpl(weatherTestApi)
  
//  @Test
//  fun `test getWeather calls api and saves data to db upon success`() {
//    runBlocking {
//      whenever(connectivity.hasNetworkAccess()).thenReturn(true)
//      whenever(weatherTestApi.getWeather(OSIJEK_CITY_NAME)).thenReturn(Response.success(successWeatherInfoResponse))
//      whenever(weatherDao.updateWeatherAndReturn(successWeatherInfoResponse.mapToRoomEntity())).thenReturn(fakeWeatherEntity)
//      weatherRepository.getWeather(OSIJEK_CITY_NAME)
//
//      verify(weatherTestApi, times(1)).getWeather(OSIJEK_CITY_NAME)
//      verify(weatherDao, times(1)).updateWeatherAndReturn(fakeWeatherEntity)
//    }
//  }
//
//  @Test
//  fun `test getWeather calls api and returns cached data from db upon failure`() {
//    runBlocking {
//      whenever(connectivity.hasNetworkAccess()).thenReturn(true)
//      whenever(weatherTestApi.getWeather(OSIJEK_CITY_NAME))
//          .thenReturn(Response.error(FAKE_FAILURE_ERROR_CODE, failureResponseBody))
//      weatherRepository.getWeather(OSIJEK_CITY_NAME)
//
//      verify(weatherTestApi, times(1)).getWeather(OSIJEK_CITY_NAME)
//      verify(weatherDao, never()).updateWeatherAndReturn(fakeWeatherEntity)
//    }
//  }

}