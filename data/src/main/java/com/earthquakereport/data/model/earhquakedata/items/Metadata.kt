package com.earthquakereport.data.model.earhquakedata.items

data class Metadata(
    val api: String,
    val count: Int,
    val generated: Long,
    val limit: Int,
    val offset: Int,
    val status: Int,
    val title: String,
    val url: String
)