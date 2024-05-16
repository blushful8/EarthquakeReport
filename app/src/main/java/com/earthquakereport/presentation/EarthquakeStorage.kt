package com.earthquakereport.presentation

import android.content.Context
import android.graphics.Color
import com.earthquakereport.R

class EarthquakeStorage(context: Context) {
    private val key = "SHARED_PREFS_KEY"
    private val sharedPreferences = context.getSharedPreferences(key, 0)

    private val keyFontStyle = "font_style"
    private val keyTopFieldColor = "top_field_color"
    private val keyTextColor = "text_color"
    private val keyPermission = "permission"
    private val keyNotifications = "notifications"

    fun getPermission() = sharedPreferences.getBoolean(keyPermission, false)
    fun savePermission(answer: Boolean) =
        sharedPreferences.edit().putBoolean(keyPermission, answer).apply()

    fun getTopFieldColor() = sharedPreferences.getInt(keyTopFieldColor, Color.WHITE)
    fun getFontStyle() = sharedPreferences.getInt(keyFontStyle, R.font.adventuro)
    fun getTextColor() = sharedPreferences.getInt(keyTextColor, Color.BLACK)

    fun saveTopFieldColor(value: Int) {
        sharedPreferences.edit().putInt(keyTopFieldColor, value).apply()
    }

    fun saveFontStyle(value: Int) {
        sharedPreferences.edit().putInt(keyFontStyle, value).apply()
    }

    fun saveTextColor(value: Int) {
        sharedPreferences.edit().putInt(keyTextColor, value).apply()
    }

    fun saveNotifications(boolean: Boolean) {
        sharedPreferences.edit().putBoolean(keyNotifications, boolean).apply()
    }

    fun getNotifications(): Boolean {
        return sharedPreferences.getBoolean(keyNotifications, false)
    }
}