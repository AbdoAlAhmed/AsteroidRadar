package com.udacity.asteroidradar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.*
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.data.sql.AsteroidDatabase
import com.udacity.asteroidradar.data.sql.asDomainModel
import com.udacity.asteroidradar.data.sql.asPodModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject


class Repo(private val database: AsteroidDatabase) {


    val getAsteroid: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroidFromDatabase()) {
            it.asDomainModel()
        }
    val getPictureOfDay : LiveData<PictureOfDay> =
        Transformations.map(database.asteroidDao.getPictureODay()){
            it?.asPodModel()
        }

    suspend fun insertAsteroid(
        startData: String = "2015-09-07",
        endData: String = "2015-09-08"
    ) {
        withContext(Dispatchers.IO) {


            val dataFromServices = AsteroidApi.asteroidServicesApi.getAsteroids(
                startData, endData, Constants.API_KEY
            )
            if (dataFromServices.isSuccessful) {
                dataFromServices.body()?.let {

                    val datalist = parseAsteroidsJsonResult(JSONObject(it))
                    database.asteroidDao.insertAsteroids(*datalist.asDatabaseModel())
                }
            }
        }
    }

    suspend fun insertPicture(){
        withContext(Dispatchers.IO){
            val dataFromServices = AsteroidApi.asteroidServicesApi.getPicture(Constants.API_KEY)
            if (dataFromServices.isSuccessful){
                dataFromServices.body()?.let {
                    database.asteroidDao.insertPicture(it.asPodEntity())
                }
            }
        }
    }
}