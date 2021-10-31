package com.example.searchgooglebooks.api

import com.example.searchgooglebooks.data.model.GoogleBook
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchGoogleBookApi {

    @GET("/books/v1/volumes")
    suspend fun searchGoogleBook(@Query("q") query: String): Response<GoogleBook>
}