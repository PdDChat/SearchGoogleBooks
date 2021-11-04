package com.example.searchgooglebooks.api

import com.example.searchgooglebooks.data.model.GoogleBook
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchBookApi {

    @GET("/books/v1/volumes")
    fun searchGoogleBook(@Query("q") query: String): Single<GoogleBook>
}