package com.one.russell.newsapp.repository

import com.one.russell.newsapp.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("everything")
    fun getArticles(
            @Query("apiKey") apiKey: String,
            @Query("page") page: Int,
            @Query("pageSize") pageSize: Int,
            @Query("domains") domains: String
    ): Call<Response>
}
