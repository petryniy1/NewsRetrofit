package com.sialitski.di

import androidx.room.Room
import com.sialitski.data.storage.retrofit.RetrofitClient
import com.sialitski.data.storage.dao.AppDatabase
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "NEWSDB"
        ).build()
    }

    single { get<AppDatabase>().getNewsDao() }

    single { RetrofitClient.getNewsApi() }
}