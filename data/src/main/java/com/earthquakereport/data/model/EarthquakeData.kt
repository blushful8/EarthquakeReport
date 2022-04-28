package com.earthquakereport.data.model


data class EarthquakeData(
    val alert: Any,
    val cdi: Any,
    val code: String,
    val detail: String,
    val dmin: Double,
    val felt: Any,
    val gap: Double,
    val ids: String,
    val mag: Double,
    val magType: String,
    val mmi: Any,
    val net: String,
    val nst: Int,
    val place: String,
    val rms: Double,
    val sig: Int,
    val sources: String,
    val status: String,
    val time: Long,
    val title: String,
    val tsunami: Int,
    val type: String,
    val types: String,
    val tz: Any,
    val updated: Long,
    val url: String
)