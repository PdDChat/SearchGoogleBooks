package com.example.searchgooglebooks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgooglebooks.data.model.Items
import com.example.searchgooglebooks.databinding.ListBookBinding

class BookListAdapter(private val listener: OnItemClickListener) : ListAdapter<Items, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    interface OnItemClickListener { fun onItemClick(items: Items) }

    inner class BookListViewHolder(binding: ListBookBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.bookTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val binding = ListBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookListViewHolder) {
            val item = getItem(position)
            holder.title.apply {
                text = item.volumeInfo.title
                setOnClickListener { listener.onItemClick(item) }
            }
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Items>() {
    override fun areContentsTheSame(oldItem: Items, newItem: Items) = oldItem == newItem
    override fun areItemsTheSame(oldItem: Items, newItem: Items) = oldItem.id == newItem.id
}