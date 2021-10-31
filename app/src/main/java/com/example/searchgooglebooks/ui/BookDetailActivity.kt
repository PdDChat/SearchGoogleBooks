package com.example.searchgooglebooks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.searchgooglebooks.R

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val bookTitle = intent.getStringExtra("book_title")
        val bookText: TextView = findViewById(R.id.detail_book_title)
        bookText.text = bookTitle
    }
}