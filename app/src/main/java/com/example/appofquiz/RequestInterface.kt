package com.example.appofquiz

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RequestInterface{
    @GET("api/questions?")
    open fun getJson(
        @Query("categories") key: String?,
        @Query("limit") key1: String?,
        @Query("difficulty") key2: String?
    ): Call<List<QuizModel?>?>
}