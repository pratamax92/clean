package com.example.data.utils

import com.example.data.database.model.WeatherEntity
import com.example.data.networking.response.MainInfo
import com.example.data.networking.response.WeatherResponse
import okhttp3.MediaType
import okhttp3.ResponseBody

const val OSIJEK_CITY_NAME = "Osijek"
const val FAKE_FAILURE_ERROR_CODE = 400

val successWeatherInfoResponse = WeatherResponse(0, arrayListOf(), MainInfo(), "")
val failureResponseBody = ResponseBody.create(MediaType.parse("text"), "network error")
val fakeWeatherEntity = WeatherEntity(0, arrayListOf(), MainInfo(), "")
