package com.example.finaltask2021.data.repository

import com.example.finaltask2021.data.database.WordsDao
import com.example.finaltask2021.data.remote.WordsApi
import com.example.finaltask2021.data.remote.dto.word_detailed.WordDto
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.model.WordDetailed
import com.example.finaltask2021.domain.model.toWord
import com.example.finaltask2021.domain.repository.WordRepository
import kotlin.random.Random

class WordRepositoryImpl(private val wordsDao: WordsDao, private val wordsApi: WordsApi) : WordRepository {

    //TODO("Save in cache or make call throw API")

    override suspend fun getRandomWord(): Word {
        return wordsDao.getById(0) ?: wordsApi.getRandomWord().toWord()
    }

    override suspend fun getWord(word: String): Word {
        return wordsDao.get(word) ?: wordsApi.getWord(word).toWord()
    }

    override suspend fun getRandomWordDetailed(): WordDetailed {
        TODO("Not yet implemented")
    }

    override suspend fun getWordDetailed(word: String): WordDetailed {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomWordDto(): WordDto {
        TODO("Not yet implemented")
    }

    override suspend fun getWordDto(): WordDto {
        TODO("Not yet implemented")
    }
}