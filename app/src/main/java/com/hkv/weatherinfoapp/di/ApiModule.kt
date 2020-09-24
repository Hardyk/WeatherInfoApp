package com.hkv.weatherinfoapp.di

import com.hkv.weatherinfoapp.network.WeatherApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideWeatherByCity(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
    single { provideWeatherByCity(get()) }

}