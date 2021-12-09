package com.example.finaltask2021.di

import com.example.finaltask2021.data.remote.WORDS_BASE_URL
import com.example.finaltask2021.data.remote.WordsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single<WordsApi> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .client(get())
            .baseUrl(WORDS_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(WordsApi::class.java)
    }
}