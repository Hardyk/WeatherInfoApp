package com.hkv.weatherinfoapp.di

import android.app.Application
import androidx.room.Room
import com.hkv.weatherinfoapp.database.AppDatabase
import com.hkv.weatherinfoapp.database.dao.CityLocationDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "LOCATION_DB")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: AppDatabase): CityLocationDao {
        return  database.cityLocationDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }


}