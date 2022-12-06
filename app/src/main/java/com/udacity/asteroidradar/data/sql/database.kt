package com.udacity.asteroidradar.data.sql

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.Asteroid


@Dao
interface AsteroidDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsteroids(vararg asteroidEntity: AsteroidEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPicture(podEntity: PodEntity)

    @Query("SELECT * FROM asteroid_table ORDER BY close_Approach_Date ASC")
    fun getAsteroidFromDatabase(): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM pod_entity ORDER BY day DESC LIMIT 1")
    fun getPictureODay(): LiveData<PodEntity>

}

@Database(entities = [AsteroidEntity::class, PodEntity::class], version = 3, exportSchema = false)
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
                        AsteroidDatabase::class.java, "asteroid_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return Instance as AsteroidDatabase
        }
    }
}