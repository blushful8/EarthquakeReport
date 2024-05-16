package com.earthquakereport.data.model.earhquakedata

import com.earthquakereport.data.model.earhquakedata.items.Feature
import com.earthquakereport.data.model.earhquakedata.items.Metadata

data class EarthquakeReportData(
    val bbox: List<Float>,
    val features: List<Feature>,
    val metadata: Metadata,
    val type: String
)