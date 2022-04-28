package com.earthquakereport.presentation.di

import com.earthquakereport.domain.Repository
import com.earthquakereport.domain.usecase.GetListOfEarthquakesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetListOfEarthquakeUseCase(repository: Repository): GetListOfEarthquakesUseCase =
        GetListOfEarthquakesUseCase.Base(repository = repository)
}