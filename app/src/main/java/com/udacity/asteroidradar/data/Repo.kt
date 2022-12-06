package com.udacity.asteroidradar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.*
import com.udacity.asteroidradar.api.endDataFormatted
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.api.startDataFormatted
import com.udacity.asteroidradar.data.sql.AsteroidDatabase
import com.udacity.asteroidradar.data.sql.asDomainModel
import com.udacity.asteroidradar.data.sql.asPodModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject


class Repo(private val database: AsteroidDatabase) {


    val getAsteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }
    val getAsteroidToday: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroidToday(endDataFormatted())) {
            it.asDomainModel()
        }
    val getAsteroidWeek: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroidWeek(endDataFormatted())) {
            it.asDomainModel()
        }

    val getPictureOfDay: LiveData<PictureOfDay> =
        Transformations.map(database.asteroidDao.getPictureODay()) {
            it?.asPodModel()
        }

    suspend fun insertAsteroid() {
        withContext(Dispatchers.IO) {


            val dataFromServices = AsteroidApi.asteroidServicesApi.getAsteroids(
                startDataFormatted(), endDataFormatted(), Constants.API_KEY
            )
            if (dataFromServices.isSuccessful) {
                dataFromServices.body()?.let {

                    val datalist = parseAsteroidsJsonResult(JSONObject(it))
                    database.asteroidDao.insertAsteroids(*datalist.asDatabaseModel())
                }
            }
        }
    }


    suspend fun insertPicture() {
        withContext(Dispatchers.IO) {
            val dataFromServices = AsteroidApi.asteroidServicesApi.getPicture(Constants.API_KEY)
            if (dataFromServices.isSuccessful) {
                dataFromServices.body()?.let {
                    database.asteroidDao.insertPicture(it.asPodEntity())
                }
            }
        }
    }
}