package com.example.searchgooglebooks.data.repository

import com.example.searchgooglebooks.api.SearchBookApi
import com.example.searchgooglebooks.data.model.GoogleBook
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchBookRepository {

    private val baseUrl: String = "https://www.googleapis.com"

    suspend fun getGoogleBooks(query: String): Response<GoogleBook> {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val service = retrofit.create(SearchBookApi::class.java)
        return service.searchGoogleBook(query)
    }
}