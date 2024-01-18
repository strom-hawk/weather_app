package com.example.domain.data

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

/**
 * Created by Saurav Suman on 18/01/24.
 */
@Keep
@Serializable
data class ForecastModel(
    val list: List<DayDataModel>
)

@Keep
@Serializable
data class DayDataModel(
    val dt: Long,
    val main: MainForecast
)

@Keep
@Serializable
data class MainForecast(
    val temp: Double,
)

@Keep
@Serializable
data class ForeCastUIModel(
    val name: String,
    val temp: String
)