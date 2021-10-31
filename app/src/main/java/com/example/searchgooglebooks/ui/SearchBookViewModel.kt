package com.example.searchgooglebooks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchgooglebooks.data.model.Items
import com.example.searchgooglebooks.data.repository.SearchBookRepository
import kotlinx.coroutines.launch

class SearchBookViewModel: ViewModel() {

    enum class ApiState {
        NONE,
        LOADING,
        SUCCESS,
        ERROR
    }

    private var _bookList: MutableLiveData<List<Items>> = MutableLiveData()
    val bookList: LiveData<List<Items>> = _bookList

    private var _apiState: MutableLiveData<ApiState> = MutableLiveData(ApiState.NONE)
    val apiState: LiveData<ApiState> = _apiState

    private val repository: SearchBookRepository = SearchBookRepository()

    fun searchGoogleBooks(query: String) {
        viewModelScope.launch {
            _apiState.postValue(ApiState.LOADING)

            val response = repository.getGoogleBooks(query)
            if (response.isSuccessful) {
                _apiState.postValue(ApiState.SUCCESS)
                _bookList.postValue(response.body()?.items)
            } else {
                _apiState.postValue(ApiState.ERROR)
            }
        }
    }
}