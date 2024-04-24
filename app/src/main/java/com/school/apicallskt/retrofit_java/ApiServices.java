package com.school.apicallskt.retrofit_java;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.school.apicallskt.retrofit_java.model.CommentsModel;
import com.school.apicallskt.retrofit_java.model.PostJvModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServices {
    @GET("posts")
    Call<JsonArray>getPosts();

    @GET("posts")
    Call<List<PostJvModel>>getPostsList();

    @GET("posts/{id}")
    Call<JsonObject>getSinglePost(@Path("id") int id);

    @GET("posts/{id}/comments")
    Call<List<CommentsModel>>getComments(@Path("id") int id);

}
