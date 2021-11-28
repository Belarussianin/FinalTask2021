package com.example.finaltask2021.domain.use_case

import com.example.finaltask2021.common.Resource
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetWordUseCase(private val repository: WordRepository) {

    operator fun invoke(word: String): Flow<Resource<Word>> = flow {
        try {
            emit(Resource.Loading())
            val coins = if (word == "") repository.getRandomWord() else repository.getWord(word)
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}