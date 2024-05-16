package com.earthquakereport.presentation


import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat


object UISetter {
    fun setFontStyle(earthquakeStorage: EarthquakeStorage, vararg textView: TextView) {
        val style = earthquakeStorage.getFontStyle()

        textView.forEach {
            it.typeface = ResourcesCompat.getFont(it.context, style)
        }
    }

    fun setTextColor(earthquakeStorage: EarthquakeStorage, vararg textViews: TextView) {
        val textColor = earthquakeStorage.getTextColor()
        textViews.forEach {
            it.setTextColor(textColor)
        }
    }

    fun setTopColor(earthquakeStorage: EarthquakeStorage, vararg topView: ViewGroup) {
        val topColor = earthquakeStorage.getTopFieldColor()
        topView.forEach {
            it.setBackgroundColor(topColor)
        }
    }

}