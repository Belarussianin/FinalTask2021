package com.example.finaltask2021.di

import com.example.finaltask2021.data.database.WordsDatabase
import com.example.finaltask2021.data.repository.WordRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val appModule = module {
    single { CoroutineScope(SupervisorJob()) }
    // Room Database
    single { WordsDatabase.getDatabase(get(), get()) }
    // Repository
    single { WordRepositoryImpl(get<WordsDatabase>().quizDao(), get()) }
}