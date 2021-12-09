package com.example.finaltask2021.domain.use_case

import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.last

class GetAllWordsUseCase(private val repository: WordRepository) {
    suspend operator fun invoke(): Flow<List<Word>> {
        return repository.getAllWordsFromCache().distinctUntilChanged()
    }
}
