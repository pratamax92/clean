package com.cartenz.feature_weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cartenz.component_base_network.coroutine.TestCoroutineContextProvider
import com.cartenz.feature_weather.ui.weatherMenu.WeatherMenuViewModel
import com.example.domain.interaction.weather.GetWeatherUseCase
import com.example.domain.data.WeatherInfo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class WeatherViewModelTest {
  
  private val getWeather: GetWeatherUseCase = mock()
  private val coroutineContext = TestCoroutineContextProvider()
  private val weatherViewModel by lazy {
    WeatherMenuViewModel(
      getWeather
    )
  }
  
  @get:Rule
  val rule: TestRule = InstantTaskExecutorRule()
  @get:Rule
  val mockitoRule: MockitoRule = MockitoJUnit.rule()
  
  @Test
  fun `test getWeather sets liveData value when success`() = runBlocking {
    whenever(getWeather(OSIJEK_CITY_NAME)).thenReturn(
        com.cartenz.component_base_domain.Success(
            WeatherInfo(
              TEMP,
              HUMIDITY,
              PRESSURE
            )
        )
    )
    weatherViewModel.getWeatherForLocation(OSIJEK_CITY_NAME)
  }
}