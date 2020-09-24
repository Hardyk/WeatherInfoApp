package com.hkv.weatherinfoapp.di

import com.hkv.weatherinfoapp.ui.cityweather.CityWeatherViewModel
import com.hkv.weatherinfoapp.ui.home.HomeViewModel
import com.hkv.weatherinfoapp.ui.map.MapsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build CountriesViewModel
    viewModel {
        HomeViewModel(repository = get())
    }

    viewModel {
        MapsViewModel(repository = get())
    }

    viewModel {
        CityWeatherViewModel(repository = get())
    }

}