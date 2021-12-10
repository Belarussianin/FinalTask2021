package com.example.finaltask2021.presentation.ui.screens.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaltask2021.data.remote.dto.word_detailed.Definition
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.use_case.SaveWordUseCase
import kotlinx.coroutines.launch

class AddNoteViewModel(
    private val saveWordUseCase: SaveWordUseCase
) : ViewModel() {

    fun saveNewWord(word: String, definition: String) {
        viewModelScope.launch {
            saveWordUseCase(
                Word(
                    word = word,
                    results = null,
                    definition = definition
                )
            )
        }
    }
}