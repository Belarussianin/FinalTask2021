package com.example.finaltask2021.data.remote

import com.example.finaltask2021.data.remote.dto.word_detailed.WordDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WordsApi {

    @GET("?random=true")
    @Headers("x-rapidapi-key:0032250002msh971b33ed3d93564p1ca791jsn1f64912e1c9f", "x-rapidapi-host:wordsapiv1.p.rapidapi.com")
    suspend fun getRandomWord(): WordDto

    @GET("{word}")
    suspend fun getWord(@Path(value="word") word: String): WordDto

}