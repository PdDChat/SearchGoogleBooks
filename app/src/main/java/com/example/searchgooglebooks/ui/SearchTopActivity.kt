package com.example.searchgooglebooks.ui

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchgooglebooks.data.model.Items
import com.example.searchgooglebooks.databinding.ActivitySearchTopBinding
import com.example.searchgooglebooks.ui.SearchBookViewModel.ApiState.*


class SearchTopActivity : AppCompatActivity(), BookListAdapter.OnItemClickListener {

    private lateinit var binding: ActivitySearchTopBinding

    private lateinit var viewModel: SearchBookViewModel
    private lateinit var adapter: BookListAdapter

    private var inputMethodManager: InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchTopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        setUpObserve()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // 背景がタップされたらキーボードを隠す
        inputMethodManager?.hideSoftInputFromWindow(binding.searchTopMain.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        binding.searchTopMain.requestFocus()

        return super.dispatchTouchEvent(ev)
    }

    override fun onItemClick(items: Items) {
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra("book_title", items.volumeInfo.title)
        intent.putExtra("book_description", items.volumeInfo.description)
        startActivity(intent)
    }

    private fun setupView() {
        inputMethodManager = getSystemService()

        binding.searchButton.setOnClickListener {
            val query = binding.inputText.text.toString()
            viewModel.searchGoogleBooks(query)
        }

        adapter = BookListAdapter(this)
        val recyclerView = binding.bookListRecyclerView
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = adapter
    }

    private fun setUpObserve() {
        viewModel = SearchBookViewModel()
        viewModel.bookList.observe(this, {
            adapter.submitList(it)
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