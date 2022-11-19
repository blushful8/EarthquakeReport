package com.earthquakereport.domain.model


import io.realm.annotations.RealmClass
import io.realm.kotlin.types.RealmObject

@RealmClass
class RealmModel : RealmObject {
    var mag: Float? = 0f
    var place: String? = null
    var time: Long? = 0
    var url: String? = null
}