package com.example.searchgooglebooks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.searchgooglebooks.databinding.ActivityBookDetailBinding

class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding :ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookTitle = intent.getStringExtra("book_title")
        val bookDescription = intent.getStringExtra("book_description")

        binding.detailBookTitle.text = bookTitle
        binding.detailBookDescription.text = bookDescription
    }
}