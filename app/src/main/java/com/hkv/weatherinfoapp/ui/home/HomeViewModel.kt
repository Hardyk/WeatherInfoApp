package com.hkv.weatherinfoapp.ui.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hkv.weatherinfoapp.database.entityData.CityLocation
import com.hkv.weatherinfoapp.database.repository.CityRepository
import com.hkv.weatherinfoapp.utils.AppResult
import com.hkv.weatherinfoapp.utils.SingleLiveEvent
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: CityRepository) : ViewModel() {


    val showLoading = ObservableBoolean()
    val cityList = MutableLiveData<List<CityLocation>>()
    val showError = SingleLiveEvent<String>()


    fun getAllCity() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.getAllBookmarkedCity()
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    cityList.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }

    fun deleteCity(cityLocation: CityLocation) {
        viewModelScope.async {
            repository.deleteBookemarkedCity(cityLocation)
        }
    }
}