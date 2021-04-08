package com.example.crud.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crud.db.CityDB
import com.example.crud.db.CityRepository
import com.example.crud.entities.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(application: Application): AndroidViewModel(application) {

    private val repository: CityRepository
    val allCities: LiveData<List<City>>

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */

    init {

        val citiesDao = CityDB.getDatabase(application, viewModelScope).cityDao()
        repository = CityRepository(citiesDao)
        allCities= repository.allCities
    }


    fun insert(city:City) = viewModelScope.launch {
        repository.insert(city)
    }
    fun deleteAll()= viewModelScope.launch(Dispatchers.IO){repository.deleteAll()}

    fun deleteByCity(city: String )= viewModelScope.launch(Dispatchers.IO){
        repository.deleteByCity(city)
    }

    fun getCityFromCountry(country: String):LiveData<List<City>>{
      return  repository.getCityFromCountry(country)
    }

    fun getCountryFromCity(city: String): LiveData<City>{
      return repository.getCountryFromCity(city)
    }

    fun updateCity(city:City)= viewModelScope.launch(Dispatchers.IO){
        repository.updateCity(city)
    }

    fun updateCountryFromCity(city: String,country: String)=viewModelScope.launch(Dispatchers.IO){
        repository.updateCountry(city,country)}


}