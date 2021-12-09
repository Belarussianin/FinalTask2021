package com.example.finaltask2021.domain.repository

import com.example.finaltask2021.data.remote.dto.word_detailed.WordDto
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.model.WordDetailed
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    suspend fun getRandomWord(): Word
    suspend fun getWord(word: String): Word

    suspend fun getRandomWordDetailed(): WordDetailed
    suspend fun getWordDetailed(word: String): WordDetailed

    suspend fun getRandomWordDto(): WordDto
    suspend fun getWordDto(): WordDto

    suspend fun getAllWordsFromCache(): Flow<List<Word>>
    //suspend fun getByIdWordFromCache(id: Int): Word?
    suspend fun getWordFromCache(word: String): Word?
    suspend fun insertWordInCache(word: Word)
    suspend fun deleteWordInCache(word: String)
}