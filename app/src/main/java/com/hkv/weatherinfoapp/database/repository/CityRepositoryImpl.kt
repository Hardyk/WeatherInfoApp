package com.hkv.weatherinfoapp.database.repository

import android.content.Context
import android.util.Log
import com.hkv.weatherinfoapp.database.dao.CityLocationDao
import com.hkv.weatherinfoapp.database.entityData.CityLocation
import com.hkv.weatherinfoapp.network.APIConst
import com.hkv.weatherinfoapp.network.WeatherApi
import com.hkv.weatherinfoapp.network.networkDto.CityWeather
import com.hkv.weatherinfoapp.utils.*
import com.hkv.weatherinfoapp.utils.Utils.handleApiError
import com.hkv.weatherinfoapp.utils.Utils.handleSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CityRepositoryImpl(
    private val api: WeatherApi,
    private val context: Context,
    private val dao: CityLocationDao
) :
    CityRepository {

    override suspend fun getAllBookmarkedCity(): AppResult<List<CityLocation>> {
        val data = getCountriesDataFromCache()
        return run {
            AppResult.Success(data)
        }

    }

    override suspend fun insertCityLocation(cityLocation: CityLocation) {
        withContext(Dispatchers.IO) {
            dao.insertCity(cityLocation)
        }
    }

    override suspend fun deleteBookemarkedCity(cityLocation: CityLocation) {
        withContext(Dispatchers.IO) {
            dao.deleteCity(cityLocation)
        }
    }

    override suspend fun getWeatherInfo(lat: String, long: String): AppResult<CityWeather> {
        if (NetworkManager.isOnline(context)) {
            return try {
                val response = api.getWeatherInfo(lat, long,APIConst.appId, APIConst.units)
                if (response.isSuccessful) {
//                    //save the data
//                    response.body()?.let {
//                        withContext(Dispatchers.IO) { dao.add(it) }
//                    }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {
           return context.noNetworkConnectivityError()
        }
    }

    private suspend fun getCountriesDataFromCache(): List<CityLocation> {
        return withContext(Dispatchers.IO) {
            dao.getAllCity()
        }
    }


/*
This is another way of implementing where the source of data is db and api but we can always fetch from db
which will be updated with the latest data from api and also change findAll() return type to
LiveData<List<CountriesData>>
*/
    /* val data = dao.findAll()
     suspend fun getAllCountriesData() {
         withContext(Dispatchers.IO) {
             val response = api.getAllCountries()
             response.body()?.let {
                 withContext(Dispatchers.IO) { dao.add(it) }
             }
         }
     }*/

}