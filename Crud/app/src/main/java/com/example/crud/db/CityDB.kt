package com.example.crud.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.crud.dao.CityDao
import com.example.crud.entities.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(City::class), version = 2, exportSchema = false)
public abstract class CityDB : RoomDatabase() {


    abstract fun cityDao(): CityDao
    private class WordDatabaseCallback(
        private val scope: CoroutineScope): RoomDatabase.Callback() {
        override  fun onOpen(db:SupportSQLiteDatabase){
            super.onOpen(db)
            INSTANCE?.let{database->scope.launch {
                var cityDao=database.cityDao()

            // Codigo da professora comentado no video
                //delete all content here
                 cityDao.deleteAll()
                // Add sample words
                  var city= City(1,"Viana do castelo","portugal")
                 cityDao.insert(city)

            }}

        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CityDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CityDB {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityDB::class.java,
                    "Cities_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }

}