package com.kaecals.jetgzoneclone.di

import com.kaecals.viewmodel.MainViewModel
import com.kaecals.viewmodel.WebViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel{ WebViewModel(get()) }
}