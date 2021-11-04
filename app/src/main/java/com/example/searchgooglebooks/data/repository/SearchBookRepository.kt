package com.example.searchgooglebooks.data.repository

import com.example.searchgooglebooks.data.model.GoogleBook
import com.example.searchgooglebooks.api.SearchBookApi
import com.example.searchgooglebooks.api.SearchBookProvider
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class SearchBookRepository {

    private val provide: SearchBookProvider = SearchBookProvider()

    fun getGoogleBooks(query: String): Single<GoogleBook> {
        val api = provide.retrofit.create(SearchBookApi::class.java)
        return api.searchGoogleBook(query)
    }
}