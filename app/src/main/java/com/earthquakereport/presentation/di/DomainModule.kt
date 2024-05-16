package com.earthquakereport.presentation.di

import com.earthquakereport.domain.Repository
import com.earthquakereport.domain.usecase.GetListOfEarthquakesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideGetListOfEarthquakeUseCase(repository: Repository): GetListOfEarthquakesUseCase =
        GetListOfEarthquakesUseCase.Base(repository = repository)
}

