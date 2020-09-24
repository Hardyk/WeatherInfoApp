package com.hkv.weatherinfoapp.database.entityData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityLocation(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "lat") var latitude: Double,
    @ColumnInfo(name = "long") var longitude: Double
) {
    constructor(city: String, latitude: Double, longitude: Double) : this(
        0,
        city,
        latitude,
        longitude
    )
}