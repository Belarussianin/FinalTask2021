package com.example.finaltask2021.di

import com.example.finaltask2021.AppSettings
import com.example.finaltask2021.data.database.WordsDatabase
import com.example.finaltask2021.data.repository.WordRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { CoroutineScope(SupervisorJob()) }
    // App settings (DataStore)
    single { AppSettings(androidContext()) }
    // Room Database
    single { WordsDatabase.getDatabase(get(), get()) }
    // Repository
    single { WordRepositoryImpl(get<WordsDatabase>().quizDao(), get()) }
}