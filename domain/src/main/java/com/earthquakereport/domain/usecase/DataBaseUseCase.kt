package com.earthquakereport.domain.usecase

import com.earthquakereport.domain.model.EarthquakeDomain


interface DataBaseUseCase {
    suspend fun write(
        mag: Float? = 0F, place: String? = null,
        time: Long? = 0, url: String? = null
    )

    fun read(): List<EarthquakeDomain>
   suspend fun clearAll()
}