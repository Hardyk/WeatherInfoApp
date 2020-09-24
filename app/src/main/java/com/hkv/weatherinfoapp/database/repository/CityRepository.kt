package com.hkv.weatherinfoapp.database.repository

import com.hkv.weatherinfoapp.database.entityData.CityLocation
import com.hkv.weatherinfoapp.network.networkDto.CityWeather
import com.hkv.weatherinfoapp.utils.AppResult

interface CityRepository {
    suspend fun getAllBookmarkedCity(): AppResult<List<CityLocation>>

    suspend fun insertCityLocation(cityLocation: CityLocation)

    suspend fun deleteBookemarkedCity(cityLocation: CityLocation)

    suspend fun getWeatherInfo(lat: String, long: String): AppResult<CityWeather>
}