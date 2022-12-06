package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.data.Repo
import com.udacity.asteroidradar.data.sql.AsteroidDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {
    private val database = AsteroidDatabase.getInstance(application)
    private val repo = Repo(database)

    val allAsteroid: LiveData<List<Asteroid>>
        get() = repo.getAsteroids
    val asteroidToday: LiveData<List<Asteroid>>
        get() = repo.getAsteroidToday
    val asteroidWeek: LiveData<List<Asteroid>>
        get() = repo.getAsteroidWeek


    val pictureOfDay: LiveData<PictureOfDay>
        get() = repo.getPictureOfDay

    init {
        viewModelScope.launch {
            repo.insertAsteroid()
            repo.insertPicture()
        }
    }
}