package com.example.finaltask2021.common

data class UiState<T>(
    val isLoading: Boolean = true,
    val data: T? = null,
    val error: String = ""
)