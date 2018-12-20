package com.one.russell.newsapp.repository

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.one.russell.newsapp.Constants
import com.one.russell.newsapp.model.Article

class NewsDataSource(private val server: ServerApi) : PageKeyedDataSource<Int, Article>() {

    private var totalResults: Int = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        try {
            //т.к. метод выполняется только при подгрузке первой страницы, её номер всегда равен 1
            val result = server.loadArticles(1, params.requestedLoadSize)

            totalResults = result?.totalResults ?: 0
            val nextPage = if (totalResults > params.requestedLoadSize) {
                2
            } else {
                null
            }
            if (result != null) {
                callback.onResult(result.articles, null, nextPage)
            }
        } catch (e: Exception) {
            Log.e(Constants.LOG_TAG, e.message)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        try {

            val result = server.loadArticles(params.key, params.requestedLoadSize)

            val nextPage = if (params.key < totalResults / params.requestedLoadSize) {
                params.key + 1
            } else {
                null
            }
            if (result != null) {
                callback.onResult(result.articles, nextPage)
            }
        } catch (e: Exception) {
            Log.e(Constants.LOG_TAG, e.message)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        try {
            val result = server.loadArticles(params.key, params.requestedLoadSize)

            val prevPage = if (params.key > 1) {
                params.key - 1
            } else {
                null
            }
            if (result != null) {
                callback.onResult(result.articles, prevPage)
            }
        } catch (e: Exception) {
            Log.e(Constants.LOG_TAG, e.message)
        }
    }
}