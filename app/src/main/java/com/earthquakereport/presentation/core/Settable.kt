package com.earthquakereport.presentation.core

import com.earthquakereport.presentation.EarthquakeAdapter

interface Settable {
    fun setBackgroundColor(mag: Float, holder: EarthquakeAdapter.EarthquakeViewHolder)
}