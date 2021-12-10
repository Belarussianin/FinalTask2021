package com.example.finaltask2021.domain.use_case

import com.example.finaltask2021.domain.repository.WordRepository

class DeleteWordUseCase(private val repository: WordRepository) {
    suspend operator fun invoke(word: String) {
        repository.deleteWordInCache(word)
    }
}