package com.cartenz.component_base_network.di

import com.cartenz.component_base_network.utils.Connectivity
import com.cartenz.component_base_network.utils.ConnectivityImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
  factory<Connectivity> { ConnectivityImpl(androidContext()) }
}