package com.earthquakereport.presentation.core

import android.app.Application
import android.content.Intent
import com.earthquakereport.data.cloud.api.ApiService.Companion.ONESIGNAL_APP_ID
import com.earthquakereport.domain.usecase.GetListOfEarthquakesUseCase
import com.earthquakereport.presentation.worker.NotificationBackgroundService
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var getListOfEarthquakesUseCase: GetListOfEarthquakesUseCase

    companion object{
        lateinit var newGetListOfEarthquakesUseCase: GetListOfEarthquakesUseCase
    }
    override fun onCreate() {
        super.onCreate()

        Hawk.init(this).build()
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)


        newGetListOfEarthquakesUseCase = getListOfEarthquakesUseCase
        CoroutineScope(Dispatchers.IO).launch {

            OneSignal.Notifications.requestPermission(true)

        }

        startService(Intent(this, NotificationBackgroundService::class.java))
    }


}

