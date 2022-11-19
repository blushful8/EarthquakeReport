package com.earthquakereport.domain

import com.earthquakereport.domain.model.EarthquakeDomain

interface Repository {
    suspend fun getEarthquakeReport(checkFirstOpen: Boolean): List<EarthquakeDomain>
}