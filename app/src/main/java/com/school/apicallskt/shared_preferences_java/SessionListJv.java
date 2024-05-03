package com.school.apicallskt.shared_preferences_java;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SessionListJv {
    private static final String PREF_NAME = "MyPrefs";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SessionListJv(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void setMyModelList(String key, List<String> list) {
        String json = gson.toJson(list);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, json);
        editor.apply();
    }

    public List<String> getMyModelList(String key) {
        String json = sharedPreferences.getString(key, "");
        if (!json.isEmpty()) {
            Type type = new TypeToken<List<String>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }
}
