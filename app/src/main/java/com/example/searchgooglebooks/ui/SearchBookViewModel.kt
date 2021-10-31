package com.example.searchgooglebooks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchgooglebooks.data.model.Items
import com.example.searchgooglebooks.data.repository.SearchGoogleBookRepository
import kotlinx.coroutines.launch

class SearchBookViewModel: ViewModel() {

    private var _bookList: MutableLiveData<List<Items>> = MutableLiveData()
    val bookList: LiveData<List<Items>> = _bookList

    private val repository: SearchGoogleBookRepository = SearchGoogleBookRepository()

    fun searchGoogleBooks(query: String) {
        viewModelScope.launch {
            val response = repository.getGoogleBooks(query)
            if (response.isSuccessful) {
                _bookList.postValue(response.body()?.items)
            }
        }
    }
}