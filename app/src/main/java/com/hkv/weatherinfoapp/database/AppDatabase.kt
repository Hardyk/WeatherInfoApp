package com.hkv.weatherinfoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hkv.weatherinfoapp.database.dao.CityLocationDao
import com.hkv.weatherinfoapp.database.entityData.CityLocation


@Database(entities = [CityLocation::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val cityLocationDao: CityLocationDao
}