package com.example.weatherapp.di

import io.ktor.client.engine.okhttp.OkHttp
import com.example.data.network.WeatherApi
import com.example.data.repositories.WeatherRepoImpl
import com.example.domain.repositories.WeatherRepository
import com.example.domain.usecases.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.http.ContentType
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Saurav Suman on 18/01/24.
 */
@Module
@InstallIn(SingletonComponent::class)
object LandingModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
                serializer = KotlinxSerializer(json)
                acceptContentTypes = acceptContentTypes + ContentType.Any
            }
        }
    }

    @Singleton
    @Provides
    fun providesWeatherApi(
        httpClient: HttpClient
    ): WeatherApi {
        return WeatherApi(httpClient = httpClient)
    }

    @Singleton
    @Provides
    fun providesWeatherRepository(api: WeatherApi): WeatherRepository =
        WeatherRepoImpl(api)

    @Singleton
    @Provides
    fun providesWeatherUseCaseUseCase(repo: WeatherRepository): WeatherUseCase {
        return WeatherUseCase(repo)
    }
}