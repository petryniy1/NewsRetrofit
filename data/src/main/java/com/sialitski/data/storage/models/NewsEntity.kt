package com.sialitski.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}