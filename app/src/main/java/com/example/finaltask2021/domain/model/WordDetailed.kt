package com.example.finaltask2021.domain.model

import com.example.finaltask2021.data.remote.dto.word_detailed.Result
import com.example.finaltask2021.data.remote.dto.word_detailed.Syllables
import com.example.finaltask2021.data.remote.dto.word_detailed.WordDto

data class WordDetailed(
    val results: List<Result>?,
    val syllables: Syllables?,
    val word: String
)

fun WordDto.toWordDetailed() = WordDetailed(
    results = results,
    syllables = syllables,
    word = word
)