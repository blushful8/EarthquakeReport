package com.earthquakereport.data.repository

import com.earthquakereport.data.cloud.CloudDataSource
import com.earthquakereport.data.cloud.mapper.ToEarthquakeDomainMapper
import com.earthquakereport.domain.Repository
import com.earthquakereport.domain.model.EarthquakeDomain

class BaseRepository(
    private val cloudDataSource: CloudDataSource,
    private val toEarthquakeDomainMapper: ToEarthquakeDomainMapper
) : Repository {
    override suspend fun getEarthquakeReport(checkFirstOpen: Boolean): List<EarthquakeDomain> {
        val earthquakeReportData = cloudDataSource.getEarthquakeReportData()
        return if (checkFirstOpen) {
            toEarthquakeDomainMapper.map(
                earthquakeReportData,
                checkFirstOpen
            )
        } else {
            toEarthquakeDomainMapper.map(
                null,
                checkFirstOpen
            )
        }
    }
}