package com.school.apicallskt.shared_preferences_java;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.school.apicallskt.R;

import java.util.List;

public class SessionConfigJv {
    private Context context;
    private SharedPreferences sharedPreferences;

    private SessionListJv listManager;
    public SessionConfigJv(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.MySharedPrefJv), MODE_PRIVATE);
        listManager = new SessionListJv(context);
    }
    public void setStringValue(String str) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.string), str);
        editor.apply();
    }
    public String getStringValue() {
        return sharedPreferences.getString(context.getString(R.string.string), "");
    }

    // To storing List in Session Config

    public void setMyModelList(List<String> myModelList) {
        listManager.setMyModelList(context.getResources().getString(R.string.MyGrowerModelList), myModelList);
    }
    public List<String> getMyModelList() {
        return listManager.getMyModelList(context.getResources().getString(R.string.MyGrowerModelList));
    }






}
