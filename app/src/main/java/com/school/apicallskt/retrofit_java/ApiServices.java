package com.school.apicallskt.retrofit_java;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    @GET("posts")
    Call<JsonArray>getPosts();
}
