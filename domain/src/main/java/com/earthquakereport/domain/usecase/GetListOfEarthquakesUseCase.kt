package com.earthquakereport.domain.usecase

import com.earthquakereport.domain.Repository
import com.earthquakereport.domain.model.EarthquakeDomain

interface GetListOfEarthquakesUseCase {
   suspend fun execute(): List<EarthquakeDomain>

    class Base(private val repository: Repository): GetListOfEarthquakesUseCase {
        override suspend fun execute(): List<EarthquakeDomain> {
            return repository.getEarthquakeReport()
        }
    }
}