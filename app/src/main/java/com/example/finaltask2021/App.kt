package com.example.finaltask2021

import android.app.Application
import com.example.finaltask2021.di.appModule
import com.example.finaltask2021.di.networkModule
import com.example.finaltask2021.di.useCaseModule
import com.example.finaltask2021.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@App)
            modules(listOf(appModule, networkModule, useCaseModule, viewModelModule))
        }
    }
}