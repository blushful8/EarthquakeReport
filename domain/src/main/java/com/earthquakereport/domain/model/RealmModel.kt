package com.earthquakereport.domain.model



import io.realm.kotlin.types.RealmObject


class RealmModel : RealmObject {
    var mag: Float? = 0f
    var place: String? = null
    var time: Long? = 0
    var url: String? = null
}