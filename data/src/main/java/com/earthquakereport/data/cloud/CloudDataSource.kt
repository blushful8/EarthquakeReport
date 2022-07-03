package com.earthquakereport.data.cloud

import android.util.Log
import com.earthquakereport.data.cloud.api.RetrofitInstance
import com.earthquakereport.data.model.EarthquakeReportData

interface CloudDataSource {

    suspend fun getEarthquakeReportData(): EarthquakeReportData?

    class Base : CloudDataSource {

        override suspend fun getEarthquakeReportData(): EarthquakeReportData? {
            Log.d("blushful2", RetrofitInstance.api.getEarthQuakeReport().code().toString())
            return RetrofitInstance.api.getEarthQuakeReport().body()
        }
    }
}