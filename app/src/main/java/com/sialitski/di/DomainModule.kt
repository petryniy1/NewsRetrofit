package com.sialitski.di

import com.sialitski.data.repository.NewsRepositoryImpl
import com.sialitski.domain.repository.NewsRepository
import org.koin.dsl.module

val domainModule = module {
    single<NewsRepository> {
        NewsRepositoryImpl(
            newsDao = get(),
            newsApi = get()
        )
    }
}