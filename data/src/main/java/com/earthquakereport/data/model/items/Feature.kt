package com.earthquakereport.data.model.items

data class Feature(
    val geometry: Geometry,
    val id: String,
    val properties: Properties,
    val type: String
)