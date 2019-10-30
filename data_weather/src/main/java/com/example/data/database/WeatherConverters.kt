package com.example.data.database

import androidx.room.TypeConverter
import com.cartenz.core.CartenzApp.Companion.gson
import com.example.data.networking.response.MainInfo
import com.example.data.networking.response.Weather
import com.google.gson.reflect.TypeToken

class WeatherConverters {

  // Weather list converters
  
  @TypeConverter
  fun fromWeatherListToJson(list: List<Weather>?): String {
    return list?.let { gson.toJson(it) } ?: ""
  }

  @TypeConverter
  fun fromJsonToWeatherList(jsonList: String): List<Weather> {
    val listType = object : TypeToken<List<Weather>>() {}.type
    return gson.fromJson(jsonList, listType)
  }

  // MainInfo converters

  @TypeConverter
  fun fromMainInfoToJson(mainInfo: MainInfo?): String {
    return mainInfo?.let { gson.toJson(it) } ?: ""
  }

  @TypeConverter
  fun fromJsonToMainInfo(json: String): MainInfo {
    val type = object : TypeToken<MainInfo>() {}.type
    return gson.fromJson(json, type)
  }
}