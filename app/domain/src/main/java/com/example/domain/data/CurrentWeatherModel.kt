package com.example.domain.data

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

/**
 * Created by Saurav Suman on 18/01/24.
 */

@Keep
@Serializable
data class CurrentWeatherModel(
    val weather: List<Weather>? = listOf(),
    val main: Main
)

@Serializable
data class Weather(
    val id: Int? = 0,
    val main: String? = "",
    val description: String? = "",
)

@Serializable
data class Main(
    val temp: Double
)