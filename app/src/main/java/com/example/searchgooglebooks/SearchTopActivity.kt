package com.example.searchgooglebooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class SearchTopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_top)

        val inputText: EditText = findViewById(R.id.input_text)
        val searchButton: Button = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            val query = inputText.text.toString()
            Log.d("DEBUG_LOG", query)
        }
    }
}