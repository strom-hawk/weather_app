package com.example.data.network

import com.example.domain.data.CurrentWeatherModel
import com.example.domain.data.ForecastModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Saurav Suman on 18/01/24.
 */
class WeatherApi(
    private val httpClient: HttpClient
) {
    suspend fun getCurrentWeatherInfo(): CurrentWeatherModel =
        httpClient.get("https://api.openweathermap.org/data/2.5/weather?q=Bengaluru&APPID=9b8cb8c7f11c077f8c4e217974d9ee40")


    suspend fun getForecastInfo(): ForecastModel =
        httpClient.get("https://api.openweathermap.org/data/2.5/forecast?q=Bengaluru&APPID=9b8cb8c7f11c077f8c4e217974d9ee40")
}