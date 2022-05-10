package com.earthquakereport.presentation.core

import com.earthquakereport.presentation.EarthquakeAdapter

interface Clickable {
    fun clickableItemView(holder: EarthquakeAdapter.EarthquakeViewHolder, position: Int)
}