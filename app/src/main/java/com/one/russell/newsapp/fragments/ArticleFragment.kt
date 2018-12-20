package com.one.russell.newsapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one.russell.newsapp.Constants
import com.one.russell.newsapp.R

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_fragment.*

class ArticleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.article_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments ?: return

        val title = args.getString(Constants.FRAGMENT_ARG_TITLE)
        tvTitle.text = title

        val desc = args.getString(Constants.FRAGMENT_ARG_DESC)
        tvDesc.text = desc

        val date = args.getString(Constants.FRAGMENT_ARG_DATE)
        tvDate.text = date

        val url = args.getString(Constants.FRAGMENT_ARG_URL)
        tvUrl.text = url

        val imageUrl = args.getString(Constants.FRAGMENT_ARG_IMAGE_URL)
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(ivArticleImage)

        btClose.setOnClickListener {
            val activity = activity as AppCompatActivity?
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}
