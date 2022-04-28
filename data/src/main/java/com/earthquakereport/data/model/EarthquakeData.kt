package com.earthquakereport.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class EarthquakeData(
    @SerializedName("alert")
    @Expose
    val alert: Any,
    @SerializedName("cdi")
    @Expose
    val cdi: Any,
    @SerializedName("code")
    @Expose
    val code: String,
    @SerializedName("detail")
    @Expose
    val detail: String,
    @SerializedName("dmin")
    @Expose
    val dmin: Double,
    @SerializedName("felt")
    @Expose
    val felt: Any,
    @SerializedName("gap")
    @Expose
    val gap: Double,
    @SerializedName("ids")
    @Expose
    val ids: String,
    @SerializedName("mag")
    @Expose
    val mag: Double,
    @SerializedName("magType")
    @Expose
    val magType: String,
    @SerializedName("mmi")
    @Expose
    val mmi: Any,
    @SerializedName("net")
    @Expose
    val net: String,
    @SerializedName("nst")
    @Expose
    val nst: Int,
    @SerializedName("place")
    @Expose
    val place: String,
    @SerializedName("rms")
    @Expose
    val rms: Double,
    @SerializedName("sig")
    @Expose
    val sig: Int,
    @SerializedName("sources")
    @Expose
    val sources: String,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("time")
    @Expose
    val time: Long,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("tsunami")
    @Expose
    val tsunami: Int,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("types")
    @Expose
    val types: String,
    @SerializedName("tz")
    @Expose
    val tz: Any,
    @SerializedName("updated")
    @Expose
    val updated: Long,
    @SerializedName("url")
    @Expose
    val url: String
)