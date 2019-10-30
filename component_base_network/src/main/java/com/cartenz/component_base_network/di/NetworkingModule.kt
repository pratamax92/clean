package com.cartenz.component_base_network.di

import com.cartenz.component_base_network.BuildConfig
import com.cartenz.core.BaseConfigUrl
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkingModule = module {

    //converter
    single {
        val gson = GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create()
        GsonConverterFactory.create(gson) as Converter.Factory
    }

    //interceptor
    single {
        HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        .setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor }

    //client :
    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addInterceptor(get())
                .callTimeout(10, TimeUnit.SECONDS)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("" + BaseConfigUrl.getBaseUrl())
            .client(get())
            .addConverterFactory(get())
            .build()
    }
}