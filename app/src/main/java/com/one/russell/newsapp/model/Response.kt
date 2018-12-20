package com.one.russell.newsapp.model

import com.one.russell.newsapp.model.Article

data class Response (
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)