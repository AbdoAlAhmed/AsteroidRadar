package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.endDataFormatted
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.api.startDataFormatted
import com.udacity.asteroidradar.data.AsteroidApi
import com.udacity.asteroidradar.data.Repo
import com.udacity.asteroidradar.data.sql.AsteroidDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainViewModel(application: Application) : ViewModel() {
    private val database = AsteroidDatabase.getInstance(application)
    private val repo = Repo(database)

    val data :LiveData<List<Asteroid>>
     get() = repo.getAsteroid

    val pictureOfDay : LiveData<PictureOfDay>
    get() = repo.getPictureOfDay

    init {
        viewModelScope.launch {
            repo.insertAsteroid()
            repo.insertPicture()
        }
    }
}