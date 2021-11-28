package com.example.finaltask2021.presentation.ui.words

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaltask2021.common.Resource
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.use_case.GetWordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class WordViewModel(private val getWordUseCase: GetWordUseCase) : ViewModel() {

    private val _wordState = MutableStateFlow(WordState())
    val wordState: StateFlow<WordState> get() = _wordState

    init {
        getWord()
    }

    private fun getWord(word: String = "") {
        getWordUseCase(word).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _wordState.value = WordState(
                        word = result.data ?: Word(
                            word = result.message ?: "An unexpected error occured",
                            results = null
                        )
                    )
                }
                is Resource.Error -> {
                    _wordState.value = WordState(
                        word = Word("Error"),
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _wordState.value = WordState(word = Word("Loading"), isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}