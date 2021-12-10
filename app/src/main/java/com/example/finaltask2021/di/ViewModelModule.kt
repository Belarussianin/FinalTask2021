package com.example.finaltask2021.di

import com.example.finaltask2021.MainViewModel
import com.example.finaltask2021.presentation.ui.screens.about.AboutViewModel
import com.example.finaltask2021.presentation.ui.screens.dictionary.DictionaryViewModel
import com.example.finaltask2021.presentation.ui.screens.notes.AddNoteViewModel
import com.example.finaltask2021.presentation.ui.screens.words.WordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { AddNoteViewModel(get()) }
    viewModel { WordViewModel(get(), get()) }
    viewModel { DictionaryViewModel(get(), get(), get()) }
    viewModel { AboutViewModel() }
}