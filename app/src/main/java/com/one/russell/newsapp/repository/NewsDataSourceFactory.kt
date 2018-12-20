package com.one.russell.newsapp.repository

import android.arch.paging.DataSource
import com.one.russell.newsapp.model.Article

class NewsDataSourceFactory(private val server: ServerApi) : DataSource.Factory<Int, Article>() {

    override fun create(): DataSource<Int, Article> {
        return NewsDataSource(server)
    }
}