package com.tt.newsapplication.adapter

import android.R
import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tt.newsapplication.databinding.CardNewsListBinding
import com.tt.newsapplication.model.Article


class NewsListAdapter(
    var list: List<Article>,
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CardNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var newsList = list[position]

        if (newsList.author.isNullOrEmpty()) {
            holder.binding.author.text = "Author:  " + newsList.author
        }

        if (newsList.title.isNullOrEmpty()) {
            holder.binding.title.text = newsList.title
        }

        if (newsList.source.name.isNullOrEmpty()) {
            holder.binding.companyName.text = newsList.source.name
        }

        if (newsList.publishedAt.isNullOrEmpty()) {
            holder.binding.publishAt.text = newsList.publishedAt
        }
        Picasso.get().load(newsList.urlToImage).placeholder(R.drawable.ic_menu_upload)
            .into(holder.binding.contentImg)

    }

    class ViewHolder(itemView: CardNewsListBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: CardNewsListBinding

        init {
            binding = itemView
        }
    }

}






