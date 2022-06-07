package com.mishraji.myweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mishraji.myweatherapp.model.Weather
import com.mishraji.myweatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject
constructor(val repository: WeatherRepository) : ViewModel()
{

    private val _response = MutableLiveData<Weather>()
    val weatherResponse: LiveData<Weather>
    get() = _response


    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        repository.getWeather().let { response ->

            if (response.isSuccessful) {
                Log.d("tag", "getWeather: ${response.body()}")
                _response.postValue(response.body())
            } else {
                Log.d("tag", "getWeather Error: ${response.code()}")
            }
        }
    }
}