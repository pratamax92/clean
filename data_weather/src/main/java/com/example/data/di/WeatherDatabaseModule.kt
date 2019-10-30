package com.example.data.di

import androidx.room.Room
import com.example.data.database.WeatherDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val WEATHER_DB = "weather-database"

val weatherDatabaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), WeatherDatabase::class.java, WEATHER_DB).build()
    }
    factory { get<WeatherDatabase>().weatherDao() }
}