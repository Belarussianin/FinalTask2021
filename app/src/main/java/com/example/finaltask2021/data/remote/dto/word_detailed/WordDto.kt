package com.example.finaltask2021.data.remote.dto.word_detailed

data class WordDto(
    val frequency: Double?,
    val pronunciation: Pronunciation?,
    val results: List<Result>?,
    val syllables: Syllables?,
    val word: String
)