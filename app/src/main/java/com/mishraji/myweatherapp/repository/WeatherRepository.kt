package com.mishraji.myweatherapp.repository

import com.mishraji.myweatherapp.api.ApiService
import javax.inject.Inject

class WeatherRepository
@Inject
constructor(val apiService: ApiService) {
    suspend fun getWeather() = apiService.getWeather()
}
