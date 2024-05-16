package com.earthquakereport.presentation.worker

import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.earthquakereport.presentation.EarthquakeStorage
import com.earthquakereport.presentation.core.App
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(context, params) {
    private val earthquakeStorage by lazy {
        EarthquakeStorage(context)
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return super.getForegroundInfo()
    }

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {

            val myProcess = RunningAppProcessInfo()
            ActivityManager.getMyMemoryState(myProcess)
            val isInBackground = myProcess.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND

            try {
                if (isInBackground) {
                    val notificationsIsEnable = earthquakeStorage.getNotifications()
                    if (notificationsIsEnable)
                        App.newGetListOfEarthquakesUseCase.execute(true)
                    Log.d("DoWork", "success")

                } else throw java.lang.Exception("foreground worker")
                Result.success()
            } catch (e: Exception) {

                Log.d("DoWork", "retry ${e.message}")
                Result.retry()
            }

        }
    }

}

