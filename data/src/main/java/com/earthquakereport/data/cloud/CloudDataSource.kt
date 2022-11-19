package com.earthquakereport.data.cloud

import com.earthquakereport.data.cloud.api.RetrofitInstance
import com.earthquakereport.data.model.EarthquakeReportData

interface CloudDataSource {

    suspend fun getEarthquakeReportData(): EarthquakeReportData?

    class Base : CloudDataSource {
        override suspend fun getEarthquakeReportData(): EarthquakeReportData? {
            return try {
                RetrofitInstance.api.getEarthQuakeReport().body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}