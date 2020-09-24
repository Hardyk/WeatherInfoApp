package com.hkv.weatherinfoapp.di

import android.content.Context
import com.hkv.weatherinfoapp.database.dao.CityLocationDao
import com.hkv.weatherinfoapp.database.repository.CityRepository
import com.hkv.weatherinfoapp.database.repository.CityRepositoryImpl
import com.hkv.weatherinfoapp.network.WeatherApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideCountryRepository(api: WeatherApi, context: Context, dao : CityLocationDao): CityRepository {
        return CityRepositoryImpl(api, context, dao)
    }
    single { provideCountryRepository(get(), androidContext(), get()) }

}