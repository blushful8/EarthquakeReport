package com.earthquakereport.domain.usecase



import com.earthquakereport.domain.Repository
import com.earthquakereport.domain.model.EarthquakeDomain


interface GetListOfEarthquakesUseCase {
    suspend fun execute(doOnBackground: Boolean): List<EarthquakeDomain>

    class Base(private val repository: Repository) : GetListOfEarthquakesUseCase {
        override suspend fun execute(doOnBackground: Boolean): List<EarthquakeDomain> {
            return repository.getEarthquakeReport(doOnBackground)
        }
    }
}