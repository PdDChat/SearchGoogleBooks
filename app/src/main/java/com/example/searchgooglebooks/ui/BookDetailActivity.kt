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
        val bookDescription = intent.getStringExtra("book_description")

        findViewById<TextView>(R.id.detail_book_title).text = bookTitle
        findViewById<TextView>(R.id.detail_book_description).text = bookDescription
    }
}