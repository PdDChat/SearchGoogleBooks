package com.example.searchgooglebooks.data.repository

import com.example.searchgooglebooks.data.model.GoogleBook
import com.example.searchgooglebooks.api.SearchGoogleBookApi
import com.example.searchgooglebooks.api.SearchGoogleBookProvider
import retrofit2.Response

class SearchGoogleBookRepository {

    private val provide: SearchGoogleBookProvider = SearchGoogleBookProvider()

    suspend fun getGoogleBooks(query: String): Response<GoogleBook> {
        val api = provide.retrofit.create(SearchGoogleBookApi::class.java)
        return api.searchGoogleBook(query)
    }
}