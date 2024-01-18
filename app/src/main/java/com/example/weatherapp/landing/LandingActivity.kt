package com.example.weatherapp.landing

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingActivity : AppCompatActivity() {
    private val viewModel: LandingActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
    }

    override fun onResume() {
        viewModel.getCurrentWeatherInfo()
        viewModel.getForecastInfo()
        super.onResume()
    }

    private fun bindViews() {
        viewModel.imageListLiveData.observe(this) {
            binding.tvMainTemp.text = it
        }

        viewModel.foreCastList.observe(this) {
            binding.layoutFirstDay.tvDay.text = it[0].name
            binding.layoutFirstDay.tvDayTemp.text = it[0].temp

            binding.layoutSecondDay.tvDay.text = it[1].name
            binding.layoutSecondDay.tvDayTemp.text = it[1].temp

            binding.layoutThirdDay.tvDay.text = it[2].name
            binding.layoutThirdDay.tvDayTemp.text = it[2].temp

            binding.layoutForthDay.tvDay.text = it[3].name
            binding.layoutForthDay.tvDayTemp.text = it[3].temp
        }

    }
}