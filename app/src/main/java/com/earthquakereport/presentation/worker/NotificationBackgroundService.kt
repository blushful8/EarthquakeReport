package com.earthquakereport.presentation.worker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class NotificationBackgroundService: android.app.Service() {
    override fun onBind(intent: Intent?) = null

    override fun onCreate() {
        super.onCreate()

        setTimeWorkRequest()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun setTimeWorkRequest() {

        val workManager = WorkManager.getInstance(this)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
                .addTag("EarthquakeReports")
                .setConstraints(constraints)
                .build()

        workManager.cancelAllWorkByTag("EarthquakeReports")
        workManager.enqueue(uploadRequest)
    }
}