package com.example.finaltask2021.di

import com.example.finaltask2021.MainViewModel
import com.example.finaltask2021.presentation.ui.about.AboutViewModel
import com.example.finaltask2021.presentation.ui.words.WordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { WordViewModel(get()) }
    viewModel { AboutViewModel() }
}