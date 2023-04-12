package com.sialitski.domain.storage.models

data class News(
    val title: String?,
    val urlToImage: String?,

    val isChecked: Boolean = false
)