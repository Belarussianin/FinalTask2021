package com.example.finaltask2021.data.remote.dto.word_detailed

data class Result(
    val definition: String?,
    val derivation: List<String>?,
    val examples: List<String>?,
    val hasCategories: List<String>?,
    val hasInstances: List<String>?,
    val hasParts: List<String>?,
    val hasTypes: List<String>?,
    val instanceOf: List<String>?,
    val partOf: List<String>?,
    val partOfSpeech: String?,
    val synonyms: List<String>?,
    val typeOf: List<String>?,
    val antonyms: List<String>?,
    val similarTo: List<String>?,
    val hasMembers: List<String>?,
    val memberOf: List<String>?,
)