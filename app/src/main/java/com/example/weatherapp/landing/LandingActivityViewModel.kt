package com.example.weatherapp.landing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.ForeCastUIModel
import com.example.domain.usecases.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
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
    val showSnackBar = MutableLiveData<Boolean>()
    val imageListLiveData = MutableLiveData<String>()
    val foreCastList = MutableLiveData<List<ForeCastUIModel>>()

    fun getCurrentWeatherInfo() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
            showSnackBar.postValue(true)
            println("____________${throwable}")
        }) {
            imageListLiveData.postValue(useCase.getCurrentWeatherInfo())
            showSnackBar.postValue(false)
        }
    }

    fun getForecastInfo() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
            showSnackBar.postValue(true)
            println("____________${throwable}")
        }) {
            foreCastList.postValue(useCase.getForecastInfo())
            showSnackBar.postValue(false)
        }
    }
}