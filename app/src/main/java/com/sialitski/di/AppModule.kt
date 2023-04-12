package com.sialitski.di

import com.sialitski.presentation.NewsSharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{
        NewsSharedViewModel(repository = get())
    }
}