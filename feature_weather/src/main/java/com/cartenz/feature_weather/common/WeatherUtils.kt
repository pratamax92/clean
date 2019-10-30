package com.cartenz.feature_weather.common

fun convertKelvinToCelsius(tempInKelvin: Double) = String.format("%.2f", tempInKelvin - 275.15)