package com.earthquakereport.cloud.mapper

import android.util.Log
import com.earthquakereport.data.model.EarthquakeReportData
import com.earthquakereport.domain.model.EarthquakeDomain

interface ToEarthquakeDomainMapper {
    fun map(earthquakeReportData: EarthquakeReportData): List<EarthquakeDomain>

    class Base() : ToEarthquakeDomainMapper {
        override fun map(earthquakeReportData: EarthquakeReportData): List<EarthquakeDomain> {
            val list = mutableListOf<EarthquakeDomain>()
            earthquakeReportData.features.forEach {
                list.add(
                    EarthquakeDomain(
                        it.properties.mag,
                        it.properties.place,
                        it.properties.time,
                        it.properties.url
                    )
                )
            }
            Log.d("blushful4", list.toString())
            return list
        }

    }
}