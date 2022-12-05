package com.udacity.asteroidradar.data.sql

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.Asteroid


@Dao
interface AsteroidDao {


}

@Database(entities = [AsteroidEntity::class, PodEntity::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract val asteroidDao: AsteroidDao


    companion object {

        @Volatile
        private var Instance: AsteroidDatabase? = null


        fun getInstance(context: Context): AsteroidDatabase {
            synchronized(this) {


                if (Instance == null) {
                    Instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java, "Asteroid_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return Instance as AsteroidDatabase
        }
    }
}