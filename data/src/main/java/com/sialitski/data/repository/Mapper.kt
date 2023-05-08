package com.sialitski.data.repository

import com.sialitski.data.storage.models.Articles
import com.sialitski.data.storage.models.NewsEntity
import com.sialitski.domain.storage.models.News

fun NewsEntity.toNews() =
    News(
        id = id,
        title = title,
        urlToImage = urlToImage,
        isChecked = true
    )

fun News.toNewsEntity() =
    NewsEntity(
        id = id,
        title = title,
        urlToImage = urlToImage
    )

fun Articles.toNews() =
    News(
        title = title,
        urlToImage = urlToImage
    )
