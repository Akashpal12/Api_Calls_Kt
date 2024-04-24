package com.school.apicallskt.retrofit_kotlin

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPosts():Call<JsonArray>
}