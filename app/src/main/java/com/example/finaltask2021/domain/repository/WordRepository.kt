package com.example.finaltask2021.domain.repository

import com.example.finaltask2021.data.remote.dto.word_detailed.WordDto
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.model.WordDetailed

interface WordRepository {
    suspend fun getRandomWord(): Word
    suspend fun getWord(word: String): Word

    suspend fun getRandomWordDetailed(): WordDetailed
    suspend fun getWordDetailed(word: String): WordDetailed

    suspend fun getRandomWordDto(): WordDto
    suspend fun getWordDto(): WordDto
}