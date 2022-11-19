package com.earthquakereport.data.model.items

data class Properties(
    val alert: Any,
    val cdi: Any,
    val code: String,
    val detail: String,
    val dmin: Any,
    val felt: Any,
    val gap: Any,
    val ids: String,
    val mag: Float,
    val magType: String,
    val mmi: Any,
    val net: String,
    val nst: Any,
    val place: String? = null,
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