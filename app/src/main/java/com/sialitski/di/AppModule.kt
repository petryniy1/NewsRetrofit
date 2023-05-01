package com.sialitski.di

import com.sialitski.presentation.viewModels.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{
        NewsViewModel(repository = get())
    }
}