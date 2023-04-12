package com.sialitski.domain.repository

import com.sialitski.domain.storage.models.News

interface NewsRepository {
    suspend fun getNetworkNews(): List<News>

    suspend fun getDataNews(): List<News>

    suspend fun insertDataNews(news: News)

    suspend fun deleteDataNews(news: News)
}