package com.example.finaltask2021.presentation.ui.screens.words

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaltask2021.common.DataState
import com.example.finaltask2021.common.UiState
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.use_case.GetWordUseCase
import com.example.finaltask2021.domain.use_case.SaveWordUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WordViewModel(
    private val getWordUseCase: GetWordUseCase,
    private val saveWordUseCase: SaveWordUseCase
) : ViewModel() {

    private val _wordState = MutableStateFlow<UiState<Word>>(UiState())
    val wordState: StateFlow<UiState<Word>> get() = _wordState

    init {
        getWord()
    }

    fun getWord(word: String = "") {
        getWordUseCase.execute(word).onEach { result ->
            when (result) {
                is DataState.Ready -> {
                    _wordState.value = UiState(isLoading = false, data = result.data)
                }
                is DataState.Error -> {
                    _wordState.value = UiState(isLoading = false, error = result.message ?: "An unexpected error occured")
                }
                is DataState.Loading -> {
                    _wordState.value = UiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveWord(word: Word) {
        viewModelScope.launch {
            saveWordUseCase(word)
        }
    }

    fun onRightSwipe(word: Word) {
        getWord()
        saveWord(word)
    }

    fun onLeftSwipe() {
        getWord()
    }
}