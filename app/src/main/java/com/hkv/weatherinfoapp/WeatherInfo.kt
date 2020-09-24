package com.hkv.weatherinfoapp

import android.app.Application
import com.hkv.weatherinfoapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherInfo : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WeatherInfo)
            modules(
                listOf(
                    apiModule,
                    viewModelModule,
                    repositoryModule,
                    networkModule,
                    dbModule
                )
            )
        }
    }
}
