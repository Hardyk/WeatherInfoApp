package com.hkv.weatherinfoapp.ui.cityweather

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hkv.weatherinfoapp.database.repository.CityRepository
import com.hkv.weatherinfoapp.network.networkDto.CityWeather
import com.hkv.weatherinfoapp.utils.AppResult
import com.hkv.weatherinfoapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CityWeatherViewModel (private val repository: CityRepository): ViewModel() {

    val showLoading = ObservableBoolean()
    val cityWeatherData = MutableLiveData<CityWeather>()
    val showError = SingleLiveEvent<String>()


    fun getWeatherInfo(lat: String, lon: String) {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.getWeatherInfo(lat, lon)

            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    cityWeatherData.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }
}