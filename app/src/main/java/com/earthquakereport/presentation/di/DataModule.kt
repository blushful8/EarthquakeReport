package com.earthquakereport.presentation.di

import com.earthquakereport.cloud.CloudDataSource
import com.earthquakereport.cloud.mapper.ToEarthquakeDomainMapper
import com.earthquakereport.data.repository.BaseRepository
import com.earthquakereport.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideCloudDataSource(): CloudDataSource = CloudDataSource.Base()

    @Provides
    @Singleton
    fun provideToEarthquakeMapper(): ToEarthquakeDomainMapper = ToEarthquakeDomainMapper.Base()

    @Provides
    @Singleton
    fun provideRepository(
        cloudDataSource: CloudDataSource,
        toEarthquakeDomainMapper: ToEarthquakeDomainMapper
    ): Repository = BaseRepository(
        cloudDataSource = cloudDataSource,
        toEarthquakeDomainMapper = toEarthquakeDomainMapper
    )
}