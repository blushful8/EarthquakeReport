package com.earthquakereport.domain.model

data class EarthquakeDomain(
    val mag: Double,
    val place: String,
    val time: Long,
    val url: String
)