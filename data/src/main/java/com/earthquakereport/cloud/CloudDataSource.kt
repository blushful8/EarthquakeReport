package com.earthquakereport.cloud

import com.earthquakereport.cloud.api.RetrofitInstance
import com.earthquakereport.data.model.EarthquakeReportData

interface CloudDataSource {

    suspend fun getEarthquakeReportData(): EarthquakeReportData?

    class Base : CloudDataSource {

        override suspend fun getEarthquakeReportData(): EarthquakeReportData? {
            return RetrofitInstance.api.getEarthQuakeReport().body()
        }
    }
}