package com.earthquakereport.data.cloud.api

import com.earthquakereport.data.model.earhquakedata.EarthquakeReportData
import com.earthquakereport.data.model.onesignaldata.OneSignalNotificationData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @GET("fdsnws/event/1/query?format=geojson&limit=10")
    suspend fun getEarthQuakeReport(): Response<EarthquakeReportData>


    @Headers(
        "Content-Type: application/json",
        "Authorization: Basic NTdiYzQ3NWItYTc0Mi00YWM3LWI0ODMtMTUzYTZjMjA4YzZl"
    )
    @POST("https://api.onesignal.com/notifications")
    fun sendNotification(@Body notificationData: OneSignalNotificationData): Call<ResponseBody>

    companion object{
        val ONESIGNAL_APP_ID = "b7d4ca0a-108c-4fc9-a9e9-ad3dbca2a370"
    }
}