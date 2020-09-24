package com.hkv.weatherinfoapp.ui.home

import com.hkv.weatherinfoapp.database.entityData.CityLocation

interface CityItemClickListner {

    fun onItemClick(city: CityLocation)
}