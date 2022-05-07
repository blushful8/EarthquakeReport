package com.earthquakereport.domain.model

data class EarthquakeDomain(
    val mag: Float,
    val place: String,
    val time: Long,
    val url: String
)