package com.earthquakereport.presentation


import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.earthquakereport.earthquakereport.R


object CustomParser {
    private const val red = "#D51831"
    private const val blue = "#8713E1"
    private const val black = "#000000"
    private const val purple = "#BE13E1"
    private const val yellow = "#EBDF3D"


    fun parseColor(value: Int): Int {
        // 0 -> yellow
        // 1 -> red
        // 2 -> blue
        // 3 -> purple
        // 4 -> black
        var defaultColor = Color.parseColor(black)
        when (value) {
            0 -> defaultColor = Color.parseColor(yellow)
            1 -> defaultColor = Color.parseColor(red)
            2 -> defaultColor = Color.parseColor(blue)
            3 -> defaultColor = Color.parseColor(purple)
            4 -> defaultColor = Color.parseColor(black)
        }
        return defaultColor
    }

    fun setFontFamily(textView: TextView, activity: Activity, value: Int) {
        //or to support all versions use
        //or to support all versions use
        // 0 -> adventuro
        // 1 -> bearsdsons
        // 2 -> milky boba
        // 3 -> reach story
        val typeface: Typeface? = when (value) {
            0 -> ResourcesCompat.getFont(activity, R.font.adventuro)
            1 -> ResourcesCompat.getFont(activity, R.font.beardsons)
            2 -> ResourcesCompat.getFont(activity, R.font.milky_boba)
            else -> ResourcesCompat.getFont(activity, R.font.reach_story)
        }
        textView.typeface = typeface
    }
}