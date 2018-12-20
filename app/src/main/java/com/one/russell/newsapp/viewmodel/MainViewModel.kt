package com.one.russell.newsapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.one.russell.newsapp.Constants
import com.one.russell.newsapp.model.Article
import com.one.russell.newsapp.repository.NewsDataSourceFactory
import com.one.russell.newsapp.repository.ServerApi
import java.util.concurrent.Executors
import kotlin.properties.Delegates

class MainViewModel : ViewModel() {

    var articlesLiveData: LiveData<PagedList<Article>> by Delegates.notNull()

    init {
        val sourceFactory = NewsDataSourceFactory(ServerApi())

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(Constants.LIST_PAGE_SIZE)
                .build()

        articlesLiveData = LivePagedListBuilder(sourceFactory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
    }
}
