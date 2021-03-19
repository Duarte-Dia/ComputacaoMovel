package com.example.crud.db

import android.provider.SyncStateContract.Helpers.insert
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.crud.dao.CityDao
import com.example.crud.entities.City
import java.util.concurrent.Flow

class CityRepository(private val cityDao: CityDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allCities: LiveData<List<City>> = cityDao.getAlphabetizedCities()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(city: City) {
        cityDao.insert(city)
    }
}
