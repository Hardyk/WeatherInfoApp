package com.hkv.weatherinfoapp.network.networkDto

import com.google.gson.annotations.SerializedName

data class RainNetworkDto (
    @SerializedName("3h")
    val threeHourlyVolume: Double
)