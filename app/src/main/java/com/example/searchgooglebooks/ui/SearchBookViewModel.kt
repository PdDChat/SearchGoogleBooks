package com.example.searchgooglebooks.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchgooglebooks.data.model.GoogleBook
import com.example.searchgooglebooks.data.model.Items
import com.example.searchgooglebooks.data.repository.SearchBookRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

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
        repository.getGoogleBooks(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<GoogleBook> {
                override fun onSubscribe(d: Disposable) {
                    _apiState.postValue(ApiState.LOADING)
                }

                override fun onSuccess(response: GoogleBook) {
                    _apiState.postValue(ApiState.SUCCESS)
                    _bookList.postValue(response.items)
                }

                override fun onError(e: Throwable) {
                    _apiState.postValue(ApiState.ERROR)
                    Log.d("DEBUG_LOG", e.message.toString())
                }
            })
    }
}