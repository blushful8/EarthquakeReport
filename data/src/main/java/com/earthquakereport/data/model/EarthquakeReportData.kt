package com.earthquakereport.data.model

import com.earthquakereport.data.model.items.Feature
import com.earthquakereport.data.model.items.Metadata

data class EarthquakeReportData(
    val bbox: List<Float>,
    val features: List<Feature>,
    val metadata: Metadata,
    val type: String
)