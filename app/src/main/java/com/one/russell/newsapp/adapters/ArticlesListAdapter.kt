package com.one.russell.newsapp.adapters

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.one.russell.newsapp.R
import com.one.russell.newsapp.model.Article

class ArticlesListAdapter (diffCallback: DiffUtil.ItemCallback<Article>) : PagedListAdapter<Article, ArticlesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ArticlesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item)
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Article> = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldArticle: Article, newArticle: Article): Boolean {
                return oldArticle.title == newArticle.title
            }

            override fun areContentsTheSame(oldArticle: Article, newArticle: Article): Boolean {
                return oldArticle.content == newArticle.content
            }
        }
    }
}
