package com.cartenz.feature_weather.di

import com.cartenz.feature_weather.ui.weatherMenu.WeatherMenuViewModel
import com.cartenz.feature_weather.ui.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherPresentationModule = module {
  viewModel { WeatherMenuViewModel(get()) }
  viewModel { WelcomeViewModel() }
}