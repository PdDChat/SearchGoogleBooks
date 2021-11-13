package com.example.searchgooglebooks.data.repository

import com.example.searchgooglebooks.data.model.GoogleBook
import com.example.searchgooglebooks.api.SearchBookApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SearchBookRepository {

    private val baseUrl: String = "https://www.googleapis.com"

    suspend fun getGoogleBooks(query: String): Response<GoogleBook> {
        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

        val service = retrofit.create(SearchBookApi::class.java)
        return service.searchGoogleBook(query)
    }
}