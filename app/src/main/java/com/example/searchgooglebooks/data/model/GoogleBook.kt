package com.example.searchgooglebooks.data.model

data class GoogleBook(
    val items: List<Items>
)

data class Items(
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String
)