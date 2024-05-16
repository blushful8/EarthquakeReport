package com.earthquakereport.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earthquakereport.domain.model.EarthquakeDomain
import com.earthquakereport.domain.usecase.GetListOfEarthquakesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getListOfEarthquakesUseCase: GetListOfEarthquakesUseCase
) : ViewModel() {
   fun getEarthquakeList(
       block: (List<EarthquakeDomain>) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            val result = getListOfEarthquakesUseCase.execute(false)
            block.invoke(result)
        }
    }


}