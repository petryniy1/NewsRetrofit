package com.sialitski.data.repository

import com.sialitski.data.retrofit.NewsApi
import com.sialitski.data.storage.models.NewsResponse
import com.sialitski.data.storage.models.dao.NewsDao
import com.sialitski.domain.repository.NewsRepository
import com.sialitski.domain.storage.models.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(
    private val newsDao: NewsDao,
    private val newsApi: NewsApi
    ) : NewsRepository {
    override suspend fun getNetworkNews(): List<News> {
        return withContext(Dispatchers.IO) {
            downloadNetworkNews().articles.map { articles -> articles.toNews() }
        }
    }

    override suspend fun getDataNews(): List<News> {
        return withContext(Dispatchers.IO) {
            newsDao.getAll().map { newsEntity -> newsEntity.toNews() }
        }
    }

    override suspend fun insertDataNews(news: News) {
        withContext(Dispatchers.IO) {
            newsDao.insertNews(news.toNewsEntity())
        }
    }

    override suspend fun deleteDataNews(news: News) {
        newsDao.deleteNews(news.toNewsEntity())
    }

    private suspend fun downloadNetworkNews(): NewsResponse {
        return newsApi.getEverything(
            query = "",
            fromDate = null,
            toDate = null,
            language = "ru",
            sortBy = ""
        )
    }
}