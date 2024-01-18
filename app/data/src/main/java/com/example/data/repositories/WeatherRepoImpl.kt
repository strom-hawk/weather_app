package com.example.data.repositories

import com.example.data.network.WeatherApi
import com.example.domain.repositories.WeatherRepository
import javax.inject.Inject
import com.example.domain.data.CurrentWeatherModel
import com.example.domain.data.ForecastModel

/**
 * Created by Saurav Suman on 18/01/24.
 */
class WeatherRepoImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getCurrentWeatherInfo(): CurrentWeatherModel = api.getCurrentWeatherInfo()
    override suspend fun getForecastInfo(): ForecastModel = api.getForecastInfo()
}
