package com.example.weatherapp.landing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Saurav Suman on 18/01/24.
 */

@HiltViewModel
class LandingActivityViewModel @Inject constructor(
    private val useCase: WeatherUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val kelvinDifference = 273.15
    val imageListLiveData = MutableLiveData<String>()

    fun getCurrentWeatherInfo() {
        viewModelScope.launch {
            val response = useCase.getCurrentWeatherInfo()
        }
    }
}