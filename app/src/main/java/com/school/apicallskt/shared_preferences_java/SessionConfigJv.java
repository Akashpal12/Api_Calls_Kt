package com.school.apicallskt.shared_preferences_java;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.school.apicallskt.R;

public class SessionConfigJv {
    private Context context;
    private SharedPreferences sharedPreferences;
    public SessionConfigJv(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.MySharedPrefJv), MODE_PRIVATE);;
    }
    public void setStringValue(String str) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.string), str);
        editor.apply();
    }
    public String getStringValue() {
        return sharedPreferences.getString(context.getString(R.string.string), "");
    }









}
