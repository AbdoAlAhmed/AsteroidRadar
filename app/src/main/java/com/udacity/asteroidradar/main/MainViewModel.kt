package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.data.Repo
import com.udacity.asteroidradar.data.sql.AsteroidDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {
    enum class AsteroidFilter(val value: String) { TODAY("today"), SAVED("saved"), WEEK("week") }
    enum class InternetConnection(val value: String){FAIL ("fail"),SUCCESS("success")}
//    data class Filter(var asteroidFilter: AsteroidFilter = AsteroidFilter.SAVED)


    private val database = AsteroidDatabase.getInstance(application)
    private val repo = Repo(database)

    val asteroidData = repo.getAsteroidsWithFilter

    val pictureOfDay: LiveData<PictureOfDay>
        get() = repo.getPictureOfDay

    init {
        viewModelScope.launch {
            repo.insertAsteroid()
            repo.insertPicture()
        }
    }

    fun filterUpdate(filter: AsteroidFilter){
        repo.updateFilter(filter)
    }


}