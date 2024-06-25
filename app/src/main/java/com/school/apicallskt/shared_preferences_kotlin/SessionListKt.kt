package com.school.apicallskt.shared_preferences_kotlin

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.school.apicallskt.R

class SessionListKt(val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        context.getString(
            R.string.MySharedPrefKt
        ), Context.MODE_PRIVATE
    )
    private val gson = Gson()


    fun setModelList(key: String, list: List<String>) {
        val json: String = gson.toJson(list)
        sharedPreferences.edit().putString(key, json).apply()
    }
    fun getMyModelList(key: String): List<String> {
        val json = sharedPreferences.getString(key, "")
        return if (json.isNullOrEmpty()) {
            ArrayList()
        } else {
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(json, type)
        }
    }


}