package com.example.searchgooglebooks.ui

import androidx.lifecycle.*
import com.example.searchgooglebooks.data.model.Items
import com.example.searchgooglebooks.data.repository.SearchBookRepository
import kotlinx.coroutines.launch

class SearchBookViewModel(private val repository: SearchBookRepository): ViewModel() {

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

    fun searchGoogleBooks(query: String) {
        viewModelScope.launch {
            _apiState.postValue(ApiState.LOADING)

            kotlin.runCatching { repository.getGoogleBooks(query) }
                .onSuccess {
                    _apiState.postValue(ApiState.SUCCESS)
                    _bookList.postValue(it.body()?.items)
                }
                .onFailure {
                    _apiState.postValue(ApiState.ERROR)
                }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class SearchBookViewModelFactory(
    private val repository: SearchBookRepository = SearchBookRepository()
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchBookViewModel(repository) as T
    }
}
