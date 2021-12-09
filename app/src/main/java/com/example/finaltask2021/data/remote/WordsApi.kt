package com.example.finaltask2021.data.remote

import com.example.finaltask2021.data.remote.dto.word_detailed.WordDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WordsApi {

    @GET("?random=true")
    @Headers("x-rapidapi-key:$API_KEY", "x-rapidapi-host:$HOST_URL")
    suspend fun getRandomWord(): WordDto

    @GET("{word}")
    @Headers("x-rapidapi-key:$API_KEY", "x-rapidapi-host:$HOST_URL")
    suspend fun getWord(@Path(value="word") word: String): WordDto

}