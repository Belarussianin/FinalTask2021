package com.example.finaltask2021.domain.use_case

import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.repository.WordRepository

class SaveWordUseCase(private val repository: WordRepository) {
    suspend operator fun invoke(word: Word) {
        if(word.word.isNotBlank()) {
            repository.insertWordInCache(word)
        }
    }
}