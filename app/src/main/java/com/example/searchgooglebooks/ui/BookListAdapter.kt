package com.example.searchgooglebooks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgooglebooks.R
import com.example.searchgooglebooks.data.model.Items

class BookListAdapter: RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {

    private val items: MutableList<Items> = mutableListOf()

    class BookListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.book_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_book, parent, false)
        return BookListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.title.text = items[position].volumeInfo.title
    }

    override fun getItemCount() = items.size

    fun appendBookList(bookList: List<Items>) {
        items.clear()
        items.addAll(bookList)
    }
}