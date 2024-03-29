package com.example.weatherapp.landing

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
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
        hitApi()
        super.onResume()
    }

    private fun bindViews() {
        viewModel.showSnackBar.observe(this) {
            hideProgressBar()
            if(it) {
                Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_LONG)
                    .setAction("Retry") { }
                    .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                    .setAction("Retry"){ hitApi() }
                    .show()
            }
        }

        viewModel.imageListLiveData.observe(this) {
            binding.tvMainTemp.text = it
            hideProgressBar()
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
            hideProgressBar()
        }

    }

    private fun hitApi() {
        binding.progressBar.visibility = View.VISIBLE
        binding.mainLayout.visibility = View.GONE
        viewModel.getForecastInfo()
        viewModel.getCurrentWeatherInfo()
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.mainLayout.visibility = View.VISIBLE
    }
}