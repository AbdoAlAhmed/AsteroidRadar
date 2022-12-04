package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.json.AsteroidInfo2
import com.udacity.asteroidradar.network.AsteroidApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _showAsteroidInfo = MutableLiveData<List<AsteroidInfo2>>()
    val showAsteroidInfo: LiveData<List<AsteroidInfo2>>
        get() = _showAsteroidInfo

    init {
        viewModelScope.launch {
            callServices()
        }
    }

    private suspend fun callServices() {

        withContext(Dispatchers.IO) {

//            try {
//                _showAsteroidInfo.value = AseroidApi.asteroidServicesApi.getData(
//                    Constants.START_DATA,Constants.END_DATA,Constants.API_KEY
//                )
//                Log.i("list", _showAsteroidInfo.value.toString())
//            }catch (ex : Exception){
//                Log.i("list",ex.toString())
//            }
            try {

                val listImg = AsteroidApi.asteroidServicesApi.getData(
                    Constants.START_DATA, Constants.END_DATA, Constants.API_KEY
                )
                Log.i("Img", listImg.message())

            } catch (ex: Exception) {
                Log.i("Img", ex.toString())
            }


        }

    }
}