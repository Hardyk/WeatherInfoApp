package com.hkv.weatherinfoapp.network.networkDto

import com.google.gson.annotations.SerializedName


data class Sys(

    @SerializedName("sunrise") val sunrise: Double,
    @SerializedName("sunset") val sunset: Double,
    @SerializedName("country") var countryCode: String
)