package com.earthquakereport.data.cloud.db

import com.earthquakereport.domain.usecase.DataBaseUseCase
import com.earthquakereport.domain.model.EarthquakeDomain
import com.earthquakereport.domain.model.RealmModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

class DataBase : DataBaseUseCase {
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

    override suspend fun clearAll() {
        realm.write {
            deleteAll()
        }
    }
}