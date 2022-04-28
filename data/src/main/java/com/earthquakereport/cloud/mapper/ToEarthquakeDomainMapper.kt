package com.earthquakereport.cloud.mapper

import android.util.Log
import com.earthquakereport.data.model.EarthquakeReportData
import com.earthquakereport.domain.model.EarthquakeDomain

interface ToEarthquakeDomainMapper {
    fun map(earthquakeReportData: EarthquakeReportData): List<EarthquakeDomain>

    class Base() : ToEarthquakeDomainMapper {
        override fun map(earthquakeReportData: EarthquakeReportData): List<EarthquakeDomain> {
            val list = mutableListOf<EarthquakeDomain>()
//            earthquakeReportData.list.forEach {
//                list.add(
//                    EarthquakeDomain(it.mag, it.place, it.time, it.url)
//                )
//            }
//            return list
            Log.d("blushful1", earthquakeReportData.toString())
            return emptyList()
        }

    }
}