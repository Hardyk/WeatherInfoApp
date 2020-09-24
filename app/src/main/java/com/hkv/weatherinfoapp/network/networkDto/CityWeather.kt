package com.hkv.weatherinfoapp.network.networkDto

import com.google.gson.annotations.SerializedName

data class CityWeather(
	@SerializedName("id") var id: Int,
	@SerializedName("name") var name: String,
	@SerializedName("coord") var coord: Coord,
	@SerializedName("main") var main: Main,
	@SerializedName("dt") var dateTime: Long,
	@SerializedName("wind") var wind: Wind?,
	@SerializedName("weather") var weather: List<Weather>,
	@SerializedName("sys") var sys: Sys?,
	@SerializedName("clouds") var clouds: Clouds?
)