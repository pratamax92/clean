package com.cobeisfresh.template

import com.cartenz.component_base_network.di.networkingModule
import com.cartenz.component_base_network.di.repositoryModule
import com.cartenz.core.CartenzApp
import com.cartenz.feature_weather.di.weatherModule
import com.cartenz.feature_weather.di.weatherPresentationModule
import com.example.data.di.weatherDatabaseModule
import com.example.data.di.weatherNetworkingModule
import com.example.data.di.weatherRepositoryModule
import com.example.domain.di.weatherInteractionModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : CartenzApp() {
    override fun baseUrl() = "http://api.openweathermap.org/data/2.5/"

    override fun createReady() {
        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            modules(appModules + domainModules + dataModules)
        }
    }
}


val appModules = listOf(weatherPresentationModule, weatherModule)
val domainModules = listOf(weatherInteractionModule)
val dataModules = listOf(
    networkingModule, repositoryModule,
    weatherNetworkingModule, weatherRepositoryModule, weatherDatabaseModule
)
