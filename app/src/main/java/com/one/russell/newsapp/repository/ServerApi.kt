package com.one.russell.newsapp.repository

import android.util.Log
import com.one.russell.newsapp.Constants
import com.one.russell.newsapp.model.Response
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerApi {

    private var userService: UserService? = null

    init {
        val okHttpClient = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        userService = retrofit.create(UserService::class.java)
    }

    fun loadArticles(page: Int, pageSize: Int): Response? {
        val resp = userService?.getArticles(Constants.API_KEY, page, pageSize, Constants.NEWS_DOMAIN)?.execute()
        if (resp?.body()?.status == "ok") {
            return resp.body()
        }
        Log.e(Constants.LOG_TAG, resp?.errorBody()?.string())
        return null
    }
}