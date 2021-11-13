package com.example.searchgooglebooks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.searchgooglebooks.data.model.Items
import com.example.searchgooglebooks.databinding.ActivityBookDetailBinding
import com.google.gson.Gson

class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding :ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        setupView()
    }

    private fun setupView() {
        val bookItemJson = intent.getStringExtra("book_item")
        val item: Items = Gson().fromJson(bookItemJson, Items::class.java)
        binding.detailBookTitle.text = item.volumeInfo.title
        binding.detailBookDescription.text = item.volumeInfo.description
    }
}