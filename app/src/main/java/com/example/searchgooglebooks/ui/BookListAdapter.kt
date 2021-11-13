package com.example.searchgooglebooks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgooglebooks.R
import com.example.searchgooglebooks.data.model.Items

class BookListAdapter(private val listener: OnItemClickListener): ListAdapter<Items, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    interface OnItemClickListener {
        fun onItemClick(items: Items)
    }

    class BookListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.book_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_book, parent, false)
        return BookListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookListViewHolder) {
            val item = getItem(position)
            holder.title.text = item.volumeInfo.title
            holder.title.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Items>() {
    override fun areContentsTheSame(oldItem: Items, newItem: Items) = oldItem == newItem
    override fun areItemsTheSame(oldItem: Items, newItem: Items) = oldItem == newItem
}