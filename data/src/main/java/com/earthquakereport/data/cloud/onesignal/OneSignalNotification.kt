package com.earthquakereport.data.cloud.onesignal

import android.util.Log
import com.earthquakereport.data.cloud.api.ApiService
import com.earthquakereport.data.cloud.api.RetrofitInstance
import com.earthquakereport.data.model.Headings
import com.earthquakereport.data.model.onesignaldata.Contents
import com.earthquakereport.data.model.onesignaldata.OneSignalNotificationData
import com.earthquakereport.domain.model.EarthquakeDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface OneSignalNotification {

    suspend fun checkAndSendEarthquakeNotification(
        newestEarthquake: EarthquakeDomain,
        doOnBackground: Boolean
    ): Boolean


    class Base : OneSignalNotification {

        private suspend fun notificationPostRequest(
            oneSignalNotificationData: OneSignalNotificationData,
        ) {

            withContext(Dispatchers.IO) {
                val call = RetrofitInstance.api.sendNotification(oneSignalNotificationData)

                call.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("Notification", "Notification sent successfully")
                        } else {
                            Log.e(
                                "Notification",
                                "Failed to send notification: ${response.message()}"
                            )
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("Notification", "Failed to send notification", t)
                    }
                })
            }
        }

        override suspend fun checkAndSendEarthquakeNotification(
            newestEarthquake: EarthquakeDomain,
            doOnBackground: Boolean
        ): Boolean {

            return try {
                if (doOnBackground)
                    notificationPostRequest(
                        OneSignalNotificationData(
                            ApiService.ONESIGNAL_APP_ID,
                            "Earthquake Report Application",
                            Contents("Місце: ${newestEarthquake.place}\nМагнітуда: ${newestEarthquake.mag}"),
                            Headings("Earthquake Report"),
                            listOf("All")
                        )
                    )
                else throw java.lang.Exception("user in app")

                true
            } catch (e: Exception) {
                false
            }
        }

    }
}