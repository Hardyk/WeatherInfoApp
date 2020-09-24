package com.hkv.weatherinfoapp.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hkv.weatherinfoapp.database.entityData.CityLocation
import com.hkv.weatherinfoapp.database.repository.CityRepository
import com.hkv.weatherinfoapp.utils.AppResult
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MapsViewModel(private val repository: CityRepository) : ViewModel() {

    fun bookMarkCity(city: String, lat: Double, long: Double) {
        viewModelScope.async {
            repository.insertCityLocation(
                CityLocation(
                    city, lat, long
                )
            )

        }
    }

    val cityList = MutableLiveData<List<CityLocation>>()

    fun loadBookmarks() {
        viewModelScope.launch {
            viewModelScope.launch {
                val result = repository.getAllBookmarkedCity()
                when (result) {
                    is AppResult.Success -> {
                        cityList.value = result.successData
                    }
                }
            }
        }
    }

}