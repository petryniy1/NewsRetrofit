package com.sialitski.presentation.recyclerView

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sialitski.domain.OnNewsClickListener
import com.sialitski.domain.storage.models.News

class NewsAdapter(
    private val itemClickListener: OnNewsClickListener
) : RecyclerView.Adapter<NewsViewHolder>() {

    private var items: List<News> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : NewsViewHolder = NewsViewHolder.from(parent, itemClickListener)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<News>) {
        items = data
        notifyDataSetChanged()
    }

}