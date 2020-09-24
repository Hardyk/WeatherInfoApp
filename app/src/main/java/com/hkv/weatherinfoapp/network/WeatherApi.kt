package com.hkv.weatherinfoapp.network

import com.hkv.weatherinfoapp.network.networkDto.CityWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("2.5/weather")
    suspend fun getWeatherInfo(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String,
        @Query("units") units: String,
    ): Response<CityWeather>
}