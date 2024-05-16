package com.earthquakereport.data.cloud.mapper

import android.annotation.SuppressLint
import android.util.Log
import com.earthquakereport.data.cloud.db.DataBase
import com.earthquakereport.data.cloud.onesignal.OneSignalNotification
import com.earthquakereport.data.model.earhquakedata.EarthquakeReportData
import com.earthquakereport.domain.model.EarthquakeDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ToEarthquakeDomainMapper {
    suspend fun map(
        earthquakeReportData: EarthquakeReportData? = null,
        oneSignalNotification: OneSignalNotification,
        doOnBackground: Boolean
    ): List<EarthquakeDomain>

    class Base : ToEarthquakeDomainMapper {

        @SuppressLint("SuspiciousIndentation")
        override suspend fun map(
            earthquakeReportData: EarthquakeReportData?,
            oneSignalNotification: OneSignalNotification,
            doOnBackground: Boolean
        ): List<EarthquakeDomain> {
            val dataBase = DataBase()
            val dbList = dataBase.read()

            Log.d("Db", dbList.toString())
            return withContext(Dispatchers.IO) {
                try {
                    val list: MutableList<EarthquakeDomain> = mutableListOf()

                    earthquakeReportData?.features?.forEach { feature ->
                        if (feature.properties.place != null) {
                            val domain = EarthquakeDomain(
                                mag = feature.properties.mag,
                                place = feature.properties.place,
                                time = feature.properties.time,
                                url = feature.properties.url
                            )

                            list.add(domain)
                        }
                    }

                    if (dbList != list) {
                        oneSignalNotification.checkAndSendEarthquakeNotification(
                            list.first(),
                            doOnBackground
                        )

                        dataBase.clearAll()
                        list.forEachIndexed { _, earthquakeDomain ->
                            dataBase.write(
                                mag = earthquakeDomain.mag,
                                place = earthquakeDomain.place,
                                time = earthquakeDomain.time,
                                url = earthquakeDomain.url
                            )
                        }

                    }

                    list
                } catch (e: Exception) {
                    e.printStackTrace()
                    dbList.ifEmpty { emptyList() }
                }
            }
        }
    }
}