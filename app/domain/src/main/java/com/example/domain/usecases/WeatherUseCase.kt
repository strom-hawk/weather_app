package com.example.domain.usecases

import com.example.domain.data.DayDataModel
import com.example.domain.data.ForeCastUIModel
import com.example.domain.repositories.WeatherRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Created by Saurav Suman on 18/01/24.
 */
class WeatherUseCase(
    private val repo: WeatherRepository
) {
    private val timeStampFor24Hrs = 86400
    private val kelvinDifference = 273.15

    suspend fun getCurrentWeatherInfo() = repo.getCurrentWeatherInfo()
    suspend fun getForecastInfo(): List<ForeCastUIModel> {
        val foreCastList = mutableListOf<ForeCastUIModel>()
        val response = repo.getForecastInfo()

        val currentTime = System.currentTimeMillis() / 1000
        val startOfTheDayTimeStamp = getStartOfTheDayTimeStamp()

        val firstDay = startOfTheDayTimeStamp + timeStampFor24Hrs
        val secondDay = startOfTheDayTimeStamp + (2 * timeStampFor24Hrs)
        val thirdDay = startOfTheDayTimeStamp + (3 * timeStampFor24Hrs)
        val forthDay = startOfTheDayTimeStamp + (4 * timeStampFor24Hrs)
        val fifthDay = startOfTheDayTimeStamp + (5 * timeStampFor24Hrs)


        val firstDayResponse = response.list.find { it.dt in firstDay..secondDay }
        val secondDayResponse = response.list.find { it.dt in secondDay..thirdDay }
        val thirdDayResponse = response.list.find { it.dt in thirdDay..forthDay }
        val forthDayResponse = response.list.find { it.dt in forthDay..fifthDay }

        firstDayResponse?.let {
            foreCastList.add(getUIModel(it))
        }

        secondDayResponse?.let {
            foreCastList.add(getUIModel(it))
        }

        thirdDayResponse?.let {
            foreCastList.add(getUIModel(it))
        }

        forthDayResponse?.let {
            foreCastList.add(getUIModel(it))
        }

        return foreCastList
    }

    private fun getStartOfTheDayTimeStamp(): Long {
        val cal = Calendar.getInstance()
        cal.time = Date()
        cal.set(Calendar.HOUR_OF_DAY, 5)
        cal.set(Calendar.MINUTE, 30)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)

        return cal.time.time / 1000
    }

    private fun getUIModel(dayDataModel: DayDataModel): ForeCastUIModel {
        val dayName = convertTimeStampToPattern(dayDataModel.dt)
        val temp = (dayDataModel.main.temp.toInt() - kelvinDifference.toInt()).toString()


        return ForeCastUIModel(dayName, temp)
    }

    private fun convertTimeStampToPattern(
        timeStamp: Long
    ): String {
        val date = Date(timeStamp * 1000)
        val dateFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
        dateFormat.timeZone = TimeZone.getDefault()
        return dateFormat.format(date)
    }
}