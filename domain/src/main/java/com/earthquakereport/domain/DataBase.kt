package com.earthquakereport.domain

import com.earthquakereport.domain.model.EarthquakeDomain


interface DataBaseLoader {
    suspend fun write(
        mag: Float? = 0F, place: String? = null,
        time: Long? = 0, url: String? = null
    )

    fun read(): List<EarthquakeDomain>
}