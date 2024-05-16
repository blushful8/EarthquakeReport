package com.earthquakereport.data.model.earhquakedata.items

data class Feature(
    val geometry: Geometry,
    val id: String,
    val properties: Properties,
    val type: String
)