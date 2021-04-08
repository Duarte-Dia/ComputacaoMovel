package com.example.crud.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crud.entities.City


@Dao

interface CityDao {

    @Query("SELECT * FROM city_table ORDER BY city ASC")
    fun getAlphabetizedCities(): LiveData<List<City>>

    @Query("SELECT * FROM city_table WHERE country ==:country")
    fun getCityFromCountry(country: String): LiveData<List<City>>

    @Query("SELECT * FROM city_table WHERE city ==:city")
    fun getCountryFromCity(city:String):LiveData<City>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: City)

    @Query("DELETE FROM city_table")
    suspend fun deleteAll()

    @Query("DELETE FROM city_table WHERE city == :city")
    suspend fun deleteByCity(city:String)

    @Update
    suspend fun updateCity(city:City)

    @Query("UPDATE city_table SET country=:country WHERE city ==:city")
    suspend fun updateCountryFromCity(city: String,country: String)



}