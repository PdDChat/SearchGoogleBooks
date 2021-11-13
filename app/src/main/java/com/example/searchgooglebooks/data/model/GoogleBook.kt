package com.example.searchgooglebooks.data.model

data class GoogleBook(
    val items: List<Items> = listOf()
)

data class Items(
    val id: String = "",
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String = "",
    val description: String? = null
)