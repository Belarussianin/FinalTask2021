package com.example.finaltask2021.presentation.ui.words

import com.example.finaltask2021.domain.model.Word

data class WordState(
    val isLoading: Boolean = false,
    val word: Word? = null,
    val error: String = ""
)