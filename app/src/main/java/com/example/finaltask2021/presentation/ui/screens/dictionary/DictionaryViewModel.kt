package com.example.finaltask2021.presentation.ui.screens.dictionary

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaltask2021.common.UiState
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.domain.use_case.GetAllWordsUseCase
import com.example.finaltask2021.domain.use_case.SaveWordUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class DictionaryViewModel(
    private val getAllWordsUseCase: GetAllWordsUseCase,
    private val saveWordUseCase: SaveWordUseCase
) : ViewModel() {

    private val _wordListState = MutableStateFlow<UiState<List<Word>>>(UiState())

    private val _searchText = MutableStateFlow("")

    val wordListState: StateFlow<UiState<List<Word>>> get() = _wordListState

//    : Flow<UiState<List<Word>>> = combine(
//        _wordListState,
//        _searchText
//    ) { wordsList, searchedText ->
//        if (searchedText.isBlank() || wordsList.data == null) {
//            wordsList
//        } else {
//            val resultList = mutableListOf<Word>()
//            for (word in wordsList.data) {
//                if (word.word.lowercase(Locale.getDefault())
//                        .contains(searchedText.lowercase(Locale.getDefault()))
//                ) {
//                    resultList.add(word)
//                }
//            }
//            UiState(isLoading = false, data = resultList.toList())
//        }
//    }

    private val _expandedCardWordsList = MutableStateFlow(listOf<String>())
    val expandedCardWordsList: StateFlow<List<String>> get() = _expandedCardWordsList

    init {
        getAllWords()
    }

    private fun getAllWords() {
        viewModelScope.launch {
            getAllWordsUseCase()
                .onStart {
                    _wordListState.value = UiState(isLoading = true)
                }
                .onEmpty { _wordListState.value = UiState(isLoading = false, error = "Error") }
                .collectLatest { result ->
                    _wordListState.value = UiState(isLoading = false, data = result)
                }
        }
    }

    fun onSearch(text: String) {
        _searchText.value = text
    }

    fun onCardArrowClicked(cardId: String) {
        _expandedCardWordsList.value = _expandedCardWordsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
        }
    }

    fun saveWord(word: Word) {
        viewModelScope.launch {
            saveWordUseCase.invoke(word)
        }
    }
}