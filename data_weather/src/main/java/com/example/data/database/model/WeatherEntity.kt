package com.example.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.database.WEATHER_TABLE_NAME
import com.example.data.networking.response.MainInfo
import com.example.data.networking.response.Weather

@Entity(tableName = WEATHER_TABLE_NAME)
data class WeatherEntity(
    @PrimaryKey
    val id: Int? = 0,
    val weather: List<Weather>?,
    @Embedded
    val main: MainInfo?,
    val name: String? = ""
)
