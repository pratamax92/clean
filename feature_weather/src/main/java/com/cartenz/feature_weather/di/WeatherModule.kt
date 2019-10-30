package com.cartenz.feature_weather.di

import com.cartenz.component_base_network.coroutine.CoroutineContextProvider
import org.koin.dsl.module

val weatherModule = module {
    single { CoroutineContextProvider() }
}