package com.example.finaltask2021.data.remote.dto.word_detailed

data class WordDto(
    val frequency: Double?,
    val rhymes: Rhymes?,
    val rhyme: String?,
    val results: List<Result>?,
    val definitions: List<Definition>?,
    val definition: String?,
    val antonyms: List<String>?,
    val syllables: Syllables?,
    val syllable: String?,
    val word: String
)