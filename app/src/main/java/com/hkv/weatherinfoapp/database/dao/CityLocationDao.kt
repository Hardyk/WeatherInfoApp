package com.hkv.weatherinfoapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hkv.weatherinfoapp.database.entityData.CityLocation

@Dao
interface CityLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(cityLocations: CityLocation)

    @Delete
    suspend fun deleteCity(cityLocations: CityLocation)

    @Query("SELECT * FROM CityLocation")
     fun getAllCity(): List<CityLocation>
}