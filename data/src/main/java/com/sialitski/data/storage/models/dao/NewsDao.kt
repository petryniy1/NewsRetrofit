package com.sialitski.data.storage.models.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sialitski.data.storage.models.NewsEntity

@Dao
interface NewsDao {

    @Query("SELECT*FROM news")
    suspend fun getAll(): List<NewsEntity>

    @Insert
    suspend fun insertNews(news: NewsEntity)

    @Delete
    suspend fun deleteNews(news: NewsEntity)
}