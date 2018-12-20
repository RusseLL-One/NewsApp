package com.one.russell.newsapp.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.one.russell.newsapp.viewmodel.MainViewModel
import com.one.russell.newsapp.R
import com.one.russell.newsapp.adapters.ArticlesListAdapter
import com.one.russell.newsapp.model.Article
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    var model: MainViewModel by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = ArticlesListAdapter(ArticlesListAdapter.DIFF_CALLBACK)

        rvArticles.layoutManager = LinearLayoutManager(this)
        rvArticles.setHasFixedSize(true)
        rvArticles.adapter = adapter

        model.articlesLiveData.observe(this, Observer<PagedList<Article>> { issues ->
            progressBar.visibility = View.GONE
            adapter.submitList(issues)
        })

    }
}
