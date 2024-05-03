package com.school.apicallskt.shared_preferences_kotlin

import android.content.Context
import android.content.SharedPreferences
import com.school.apicallskt.R

class SessionConfigKt(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.MySharedPrefKt), Context.MODE_PRIVATE)

    fun setStringValue(str: String) {
        val editor = sharedPreferences.edit()
        editor.putString(context.getString(R.string.string), str)
        editor.apply()
    }

    fun getStringValue(): String? {
        return sharedPreferences.getString(context.getString(R.string.string), "")
    }
}
