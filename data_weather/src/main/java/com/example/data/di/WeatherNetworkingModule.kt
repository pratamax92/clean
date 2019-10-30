package com.example.data.di

import com.example.data.networking.WeatherApi
import org.koin.dsl.module
import retrofit2.Retrofit

val weatherNetworkingModule = module {
    single { get<Retrofit>().create(WeatherApi::class.java) }
}