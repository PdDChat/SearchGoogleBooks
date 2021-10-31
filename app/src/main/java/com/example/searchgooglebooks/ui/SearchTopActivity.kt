package com.example.searchgooglebooks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgooglebooks.R
import com.example.searchgooglebooks.data.model.GoogleBook

class SearchTopActivity : AppCompatActivity()  {

    private lateinit var viewModel: SearchBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_top)

        viewModel = SearchBookViewModel()

        val inputText: EditText = findViewById(R.id.input_text)
        findViewById<Button>(R.id.search_button).setOnClickListener {
            val query = inputText.text.toString()
            viewModel.searchGoogleBooks(query)
        }

        val adapter = BookListAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.book_list_recycler_view)
        recyclerView.adapter = adapter

        viewModel.bookList.observe(this, {
            Log.d("DEBUG_LOG", it[0].volumeInfo.title)
            adapter.appendBookList(it)
            adapter.notifyDataSetChanged()
        })
    }
}