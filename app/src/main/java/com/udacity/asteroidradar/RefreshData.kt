package com.udacity.asteroidradar

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.data.Repo
import com.udacity.asteroidradar.data.sql.AsteroidDatabase
import retrofit2.HttpException

class RefreshData (appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params){
    companion object {
        const val WORK_NAME = "RefreshData"
    }

    override suspend fun doWork(): Result {
        val database = AsteroidDatabase.getInstance(applicationContext)
        val repo= Repo(database)
        return try {
            repo.insertAsteroid()
            repo.insertPicture()
            Result.success()
        } catch (e: HttpException) {
           Result.retry()
        }
    }
}