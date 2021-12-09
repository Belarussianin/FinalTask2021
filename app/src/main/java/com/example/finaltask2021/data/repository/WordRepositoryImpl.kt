package com.example.finaltask2021.data.repository

import com.example.finaltask2021.data.database.WordsDao
import com.example.finaltask2021.data.remote.WordsApi
import com.example.finaltask2021.data.remote.dto.word_detailed.WordDto
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.model.WordDetailed
import com.example.finaltask2021.domain.model.toWord
import com.example.finaltask2021.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class WordRepositoryImpl(private val wordsDao: WordsDao, private val wordsApi: WordsApi) :
    WordRepository {

    //TODO("Save in cache or make call throw API")

    override suspend fun getRandomWord(): Word {
        //return wordsDao.getById(0) ?:
        return wordsApi.getRandomWord().toWord()
    }

    override suspend fun getWord(word: String): Word {
        return wordsApi.getWord(word).toWord()
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


    override suspend fun getAllWordsFromCache(): Flow<List<Word>> {
        return wordsDao.getAll()
    }

//    override suspend fun getByIdWordFromCache(id: Int): Word? {
//        return wordsDao.getById(id)
//    }

    override suspend fun getWordFromCache(word: String): Word? {
        return wordsDao.get(word)
    }

    override suspend fun insertWordInCache(word: Word) {
        wordsDao.insert(word)
    }

    override suspend fun deleteWordInCache(word: String) {
        wordsDao.delete(word)
    }
}