package com.example.finaltask2021.di

import com.example.finaltask2021.data.repository.WordRepositoryImpl
import com.example.finaltask2021.domain.use_case.GetAllWordsUseCase
import com.example.finaltask2021.domain.use_case.GetWordUseCase
import com.example.finaltask2021.domain.use_case.SaveWordUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetWordUseCase(get<WordRepositoryImpl>()) }
    factory { SaveWordUseCase(get<WordRepositoryImpl>()) }
    factory { GetAllWordsUseCase(get<WordRepositoryImpl>()) }
}