package com.example.data.di

import com.example.data.repository.weather.WeatherRepositoryImpl
import com.example.domain.repository.WeatherRepository
import org.koin.dsl.module

val weatherRepositoryModule = module {
    factory<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}