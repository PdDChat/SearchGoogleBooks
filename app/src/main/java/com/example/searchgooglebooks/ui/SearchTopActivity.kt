package com.example.searchgooglebooks.ui

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchgooglebooks.data.model.Items
import com.example.searchgooglebooks.databinding.ActivitySearchTopBinding
import com.example.searchgooglebooks.ui.SearchBookViewModel.ApiState.*
import com.google.gson.Gson


class SearchTopActivity : AppCompatActivity(), BookListAdapter.OnItemClickListener {

    private lateinit var binding: ActivitySearchTopBinding

    private val viewModel: SearchBookViewModel by lazy {
        ViewModelProvider(this, SearchBookViewModelFactory())[SearchBookViewModel::class.java]
    }

    private lateinit var bookListAdapter: BookListAdapter

    private var inputMethodManager: InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchTopBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        setupView()

        setupObserver()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // 背景がタップされたらキーボードを隠す
        inputMethodManager?.hideSoftInputFromWindow(binding.searchTopMain.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        binding.searchTopMain.requestFocus()

        return super.dispatchTouchEvent(ev)
    }

    override fun onItemClick(items: Items) {
        val intent = Intent(this, BookDetailActivity::class.java).apply {
            putExtra("book_item", Gson().toJson(items))
        }
        startActivity(intent)
    }

    private fun setupView() {
        inputMethodManager = getSystemService()

        binding.searchButton.setOnClickListener {
            val query = binding.inputText.text.toString()
            viewModel.searchGoogleBooks(query)
        }

        bookListAdapter = BookListAdapter(this)
        binding.bookListRecyclerView.apply {
            val context = this@SearchTopActivity
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(context).orientation))
            adapter = bookListAdapter
        } }

    private fun setupObserver() {
        viewModel.bookList.observe(this, {
            bookListAdapter.submitList(it)
        })

        viewModel.apiState.observe(this, { state ->
            when (state) {
                LOADING -> binding.searchBookProgress.visibility = ProgressBar.VISIBLE
                ERROR ->  {
                    binding.searchBookProgress.visibility = ProgressBar.GONE
                    Toast.makeText(this, "何らかのエラーが発生しました", Toast.LENGTH_SHORT).show()
                }
                else -> binding.searchBookProgress.visibility = ProgressBar.GONE
            }
        })
    }
}