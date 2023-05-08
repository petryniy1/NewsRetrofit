package com.sialitski.domain.storage.models

data class News(
    var id: Int = 0,
    val title: String?,
    val urlToImage: String?,
    val isChecked: Boolean = false
)