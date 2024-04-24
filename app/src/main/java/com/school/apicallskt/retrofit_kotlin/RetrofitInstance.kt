package com.school.apicallskt.retrofit_kotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    fun getClient():ApiService{
        val  retrofit =Retrofit
            .Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofit.create(ApiService::class.java)
    }
}