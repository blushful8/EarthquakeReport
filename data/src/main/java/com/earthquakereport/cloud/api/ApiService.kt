package com.earthquakereport.cloud.api

import com.earthquakereport.data.model.EarthquakeReportData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("fdsnws/event/1/query?format=geojson&limit=10")
    suspend fun getEarthQuakeReport(): Response<EarthquakeReportData>
}