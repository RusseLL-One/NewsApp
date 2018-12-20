package com.one.russell.newsapp.adapters

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.one.russell.newsapp.Constants
import com.one.russell.newsapp.activities.MainActivity
import com.one.russell.newsapp.R
import com.one.russell.newsapp.fragments.ArticleFragment
import com.one.russell.newsapp.model.Article
import com.squareup.picasso.Picasso

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val articleImageView: ImageView
    private val articleTitle: TextView
    private val articleDescription: TextView
    private val articleDate: TextView

    init {
        articleImageView = itemView.findViewById(R.id.ivArticleImage)
        articleTitle = itemView.findViewById(R.id.tvTitle)
        articleDescription = itemView.findViewById(R.id.tvDesc)
        articleDate = itemView.findViewById(R.id.tvDate)
    }

    fun bind(article: Article) {

        articleTitle.text = article.title
        articleDescription.text = article.description
        val date = convertDate(article.publishedAt)
        articleDate.text = date
        Picasso.get()
                .load(article.urlToImage)
                .placeholder(R.drawable.placeholder)
                .into(articleImageView)

        itemView.setOnClickListener { v ->
            val articleFragment = ArticleFragment()
            val fm = (v.context as MainActivity).supportFragmentManager
            val args = Bundle()
            args.putString(Constants.FRAGMENT_ARG_TITLE, article.title)
            args.putString(Constants.FRAGMENT_ARG_DESC, article.description)
            args.putString(Constants.FRAGMENT_ARG_DATE, date)
            args.putString(Constants.FRAGMENT_ARG_IMAGE_URL, article.urlToImage)
            args.putString(Constants.FRAGMENT_ARG_URL, article.url)
            articleFragment.arguments = args
            fm.beginTransaction().replace(R.id.fragment, articleFragment).addToBackStack(null).commit()
        }
    }

    private fun convertDate(dateStr: String): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

        try {
            val date = inputDateFormat.parse(dateStr)

            if (DateUtils.isToday(date.time)) {
                val outputDateFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
                return outputDateFormat.format(date)
            } else {
                val outputDateFormat = SimpleDateFormat("HH:mm / dd.MM", Locale.ENGLISH)
                return outputDateFormat.format(date)
            }

        } catch (e: ParseException) {
            Log.e(Constants.LOG_TAG, e.message)
            return ""
        }

    }
}