package com.earthquakereport.data.cloud.mapper

import android.util.Log
import com.earthquakereport.data.model.EarthquakeReportData
import com.earthquakereport.domain.DataBaseLoader
import com.earthquakereport.domain.model.EarthquakeDomain
import com.earthquakereport.domain.model.RealmModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ToEarthquakeDomainMapper {
    suspend fun map(
        earthquakeReportData: EarthquakeReportData? = null,
        checkFirstOpen: Boolean
    ): List<EarthquakeDomain>

    class DataBase : DataBaseLoader {
        private val config = RealmConfiguration.Builder(schema = setOf(RealmModel::class)).build()
        private val realm = Realm.open(config)
        private val all: RealmResults<RealmModel> = realm.query<RealmModel>().find()

        override suspend fun write(
            mag: Float?, place: String?,
            time: Long?, url: String?
        ) {
            val realmModel = RealmModel().apply {
                this.mag = mag
                this.place = place
                this.time = time
                this.url = url
            }
            realm.write { copyToRealm(realmModel) }
        }

        override fun read(): MutableList<EarthquakeDomain> {
            val list = mutableListOf<EarthquakeDomain>()
            for (i in all.indices) {
                list.add(
                    i, EarthquakeDomain(
                        mag = all[i].mag,
                        place = all[i].place,
                        time = all[i].time,
                        url = all[i].url
                    )
                )
            }
            return list
        }
    }

    class Base : ToEarthquakeDomainMapper {
        override suspend fun map(
            earthquakeReportData: EarthquakeReportData?,
            checkFirstOpen: Boolean
        ): List<EarthquakeDomain> {
            return withContext(Dispatchers.IO) {
                try {
                    var list: MutableList<EarthquakeDomain> = mutableListOf()
                    val dataBase = DataBase()
                    Log.d("First start", checkFirstOpen.toString())
                    if (checkFirstOpen) {
                        earthquakeReportData?.features?.forEach { feature ->
                            if (feature.properties.place != null) {
                                dataBase.write(
                                    mag = feature.properties.mag,
                                    place = feature.properties.place,
                                    time = feature.properties.time,
                                    url = feature.properties.url
                                )

                                list.add(
                                    EarthquakeDomain(
                                        mag = feature.properties.mag,
                                        place = feature.properties.place,
                                        time = feature.properties.time,
                                        url = feature.properties.url
                                    )
                                )
                            }
                        }
                    } else list = dataBase.read()
                    Log.d("TAG", "end list $list")
                    list
                } catch (e: Exception) {
                    e.printStackTrace()
                    mutableListOf()
                }
            }
        }
    }
}