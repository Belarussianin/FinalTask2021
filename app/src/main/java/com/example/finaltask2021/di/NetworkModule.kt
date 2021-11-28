package com.example.finaltask2021.di

import android.util.Log
import com.example.finaltask2021.common.TAG
import com.example.finaltask2021.common.WORDS_BASE_URL
import com.example.finaltask2021.data.remote.WordsApi
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BASIC
            })
//            .addInterceptor(Interceptor { chain ->
//                val original: Request = chain.request()
//                val originalHttpUrl: HttpUrl = original.url
//                val url = originalHttpUrl.newBuilder()
//                    .addQueryParameter(
//                        "x-rapidapi-key",
//                        "0032250002msh971b33ed3d93564p1ca791jsn1f64912e1c9f"
//                    )
//                    .addQueryParameter("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
//                    .build()
//                Log.i(TAG, "$url")
//                // Request customization: add request headers
//                val requestBuilder: Request.Builder = original.newBuilder()
//                    .url(url)
//                val request: Request = requestBuilder.build()
//                chain.proceed(request)
//            })
            .build()
    }

    single<WordsApi> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(WORDS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WordsApi::class.java)
    }
}