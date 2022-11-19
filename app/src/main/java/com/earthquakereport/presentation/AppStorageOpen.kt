package com.earthquakereport.presentation

import android.app.Activity

class AppStorageOpen(activity: Activity) {
    private val key = "first_open"
    private val sharedPreferences = activity.getSharedPreferences(key, 0)
    fun getResult() = sharedPreferences.getBoolean(key, true)
    fun saveResult(value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    private val keyFontStyle = "font_style"
    private val keyTopFieldColor = "top_field_color"
    private val keyTextColor = "text_color"


    fun getTopFieldColor() = sharedPreferences.getInt(keyTopFieldColor, 0)
    fun getFontStyle() = sharedPreferences.getInt(keyFontStyle, 0)
    fun getTextColor() = sharedPreferences.getInt(keyTextColor, 4)

    fun saveTopFieldColor(value: Int) {
        sharedPreferences.edit().putInt(keyTopFieldColor, value).apply()
    }

    fun saveFontStyle(value: Int) {
        sharedPreferences.edit().putInt(keyFontStyle, value).apply()
    }

    fun saveTextColor(value: Int) {
        sharedPreferences.edit().putInt(keyTextColor, value).apply()
    }
}