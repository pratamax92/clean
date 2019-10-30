package com.cartenz.core

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy

abstract class CartenzApp : Application() {

    companion object {
        lateinit var instance: Application
            set
        val gson: Gson = GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .serializeNulls().create()

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        BaseConfigUrl.setBaseUrl(baseUrl())
        createReady()
    }

    abstract fun baseUrl() : String
    abstract fun createReady()
}

