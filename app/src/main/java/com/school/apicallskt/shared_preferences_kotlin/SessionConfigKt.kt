package com.school.apicallskt.shared_preferences_kotlin

import android.content.Context
import android.content.SharedPreferences
import com.school.apicallskt.R

class SessionConfigKt(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences(context.getString(R.string.MySharedPrefKt), Context.MODE_PRIVATE)
    private val listManager= SessionListKt(context)
    fun setStringValue(str: String) {
        val editor = sharedPreferences.edit()
        editor.putString(context.getString(R.string.string), str)
        editor.apply()
    }

    fun getStringValue(): String? {
        return sharedPreferences.getString(context.getString(R.string.string), "")
    }

    // To store a List in Session Config
    fun setMyModelList(myModelList: List<String>) {
        listManager.setModelList(
            context.resources.getString(R.string.MyGrowerModelList),
            myModelList
        )
    }

    // To retrieve a List from Session Config
    fun getMyModelList(): List<String> {
        return listManager.getMyModelList(context.resources.getString(R.string.MyGrowerModelList))
    }

}
