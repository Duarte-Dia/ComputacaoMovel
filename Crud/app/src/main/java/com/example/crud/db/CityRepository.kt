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


    fun getCityFromCountry(country:String):LiveData<City>{
        return cityDao.getCityFromCountry(country)
    }
    fun getCountryFromCity(city:String):LiveData<City>{
        return cityDao.getCountryFromCity(city)
    }
    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(city: City) {
        cityDao.insert(city)
    }

    suspend fun deleteAll() {
        cityDao.deleteAll()
    }

    suspend fun deleteByCity(city: String) {
        cityDao.deleteByCity(city)
    }

    suspend fun updateCity(city: City) {
        cityDao.updateCity(city)
    }

    suspend fun updateCountry(city: String, country: String) {
        cityDao.updateCountryFromCity(city,country)
    }

}
