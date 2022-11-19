package com.earthquakereport.domain.model

data class EarthquakeDomain(
    val mag: Float? = 0F,
    val place: String? = "Error",
    val time: Long? = 0,
    val url: String? = "Error"
)