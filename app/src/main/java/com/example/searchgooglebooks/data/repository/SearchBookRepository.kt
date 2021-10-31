package com.example.searchgooglebooks.data.repository

import com.example.searchgooglebooks.data.model.GoogleBook
import com.example.searchgooglebooks.api.SearchBookApi
import com.example.searchgooglebooks.api.SearchBookProvider
import retrofit2.Response

class SearchBookRepository {

    private val provide: SearchBookProvider = SearchBookProvider()

    suspend fun getGoogleBooks(query: String): Response<GoogleBook> {
        val api = provide.retrofit.create(SearchBookApi::class.java)
        return api.searchGoogleBook(query)
    }
}