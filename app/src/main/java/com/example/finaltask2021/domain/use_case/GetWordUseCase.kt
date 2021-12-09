package com.example.finaltask2021.domain.use_case

import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.repository.WordRepository

class GetWordUseCase(private val repository: WordRepository): BaseStateUseCase<Word, String>() {

    override suspend fun run(params: String): Word {
        return if (params == "") {
            repository.getRandomWord()
        } else {
            repository.getWord(params)
        }
    }

//    operator fun invoke(word: String): Flow<State<Word>> = flow {
//        try {
//            emit(State.Loading())
//            val coins = if (word == "") repository.getRandomWord() else repository.getWord(word)
//            emit(State.Success(coins))
//        } catch (e: HttpException) {
//            emit(State.Error(e.localizedMessage ?: "An unexpected error occured"))
//        } catch (e: IOException) {
//            emit(State.Error("Couldn't reach server. Check your internet connection."))
//        }
//    }
}