package com.school.apicallskt.retrofit_kotlin

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.school.apicallskt.retrofit_kotlin.model.PostKtModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getPosts():Call<JsonArray>

    @GET("posts")
    fun getPostsList():Call<List<PostKtModel>>

    @GET("posts/{id}")
    fun getSinglePosts(@Path("id") int: Int?):Call<JsonObject>
}