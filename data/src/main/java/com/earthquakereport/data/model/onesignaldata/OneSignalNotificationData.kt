package com.earthquakereport.data.model.onesignaldata

import com.earthquakereport.data.model.Headings
import com.google.gson.annotations.SerializedName

data class OneSignalNotificationData(
    @SerializedName("app_id") val appId: String,
    @SerializedName("name") val name: String,
    @SerializedName("contents") val contents: Contents,
    @SerializedName("headings") val headings: Headings,
    @SerializedName("included_segments") val includedSegments: List<String>
)