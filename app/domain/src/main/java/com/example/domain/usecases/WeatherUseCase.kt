package com.example.domain.usecases

import com.example.domain.repositories.WeatherRepository

/**
 * Created by Saurav Suman on 18/01/24.
 */
class WeatherUseCase(
    private val repo: WeatherRepository
) {
    suspend fun getCurrentWeatherInfo() = repo.getCurrentWeatherInfo()
}