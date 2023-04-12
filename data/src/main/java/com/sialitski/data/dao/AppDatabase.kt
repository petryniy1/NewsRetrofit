package com.sialitski.data.dao

import android.os.Build.VERSION
import androidx.room.Database
import androidx.room.RoomDatabase
import com.sialitski.data.storage.models.NewsEntity


@Database(
    entities = [
        NewsEntity::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1
    }

    abstract fun getNewsDao(): NewsDao
}