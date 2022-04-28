package com.earthquakereport.presentation

import androidx.lifecycle.ViewModel
import com.earthquakereport.domain.usecase.GetListOfEarthquakesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getListOfEarthquakesUseCase: GetListOfEarthquakesUseCase
) : ViewModel() {
    suspend fun getEarthquakeList() = getListOfEarthquakesUseCase.execute()
}