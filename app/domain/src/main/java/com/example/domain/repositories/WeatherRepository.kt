package com.example.domain.repositories

import com.example.domain.data.CurrentWeatherModel
import com.example.domain.data.ForecastModel

/**
 * Created by Saurav Suman on 18/01/24.
 */
interface WeatherRepository {
    suspend fun getCurrentWeatherInfo(): CurrentWeatherModel
    suspend fun getForecastInfo(): ForecastModel
}