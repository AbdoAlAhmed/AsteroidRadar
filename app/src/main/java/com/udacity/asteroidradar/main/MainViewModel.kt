package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.internetConnection
import com.udacity.asteroidradar.data.Repo
import com.udacity.asteroidradar.data.sql.AsteroidDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {
    enum class AsteroidFilter(val value: String) { TODAY("today"), SAVED("saved"), WEEK("week") }
//    data class Filter(var asteroidFilter: AsteroidFilter = AsteroidFilter.SAVED)


    private val database = AsteroidDatabase.getInstance(application)
    private val repo = Repo(database)

    val asteroidData = repo.getAsteroidsWithFilter

    val pictureOfDay: LiveData<PictureOfDay>
        get() = repo.getPictureOfDay

    init {
        viewModelScope.launch {
       try {

           repo.insertAsteroid()
            repo.insertPicture()
       }catch (ex:Exception){
           Log.i("ex",ex.toString())
       }
        }

    }

    fun filterUpdate(filter: AsteroidFilter){
        repo.updateFilter(filter)
    }


}