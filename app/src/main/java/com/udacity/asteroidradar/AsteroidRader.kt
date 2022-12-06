package com.udacity.asteroidradar

import android.app.Application
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class AsteroidRader : Application() {
    val applicationScope = CoroutineScope(Dispatchers.Default)

    private fun setDelay() {
        applicationScope.launch {
            repeating()
        }
    }

    private fun repeating() {

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true).build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshData>(1, TimeUnit.DAYS)
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshData.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP, repeatingRequest
        )

    }

    override fun onCreate() {
        super.onCreate()
        setDelay()
    }
}