package com.example.finaltask2021.presentation.ui.screens.dictionary

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.finaltask2021.common.Process
import com.example.finaltask2021.common.TAG
import com.example.finaltask2021.common.UiState
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.presentation.components.ExpandableWordCard
import com.example.finaltask2021.presentation.components.SearchTextField
import org.koin.androidx.compose.getViewModel

@Composable
fun DictionaryScreen(
    viewModel: DictionaryViewModel = getViewModel(),
    onNavigateClick: (String) -> Unit = {}
) {
    val filteredWordsListState = viewModel.wordListState.collectAsState(UiState(isLoading = true))
    val expandedCardIds = viewModel.expandedCardWordsList.collectAsState()
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    SideEffect {
        Log.e(TAG, "DictionaryScreen: composed")
    }

    BoxWithConstraints(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        filteredWordsListState.value.Process(
            onLoading = {
                CircularProgressIndicator()
            },
            onReady = { wordsList ->
                SideEffect {
                    Log.e(TAG, "DictionaryScreen: onReady")
                }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SearchTextField(
                        state = textState,
                        onChange = {
                            viewModel.onSearch(it)
                        }
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.Top
                    ) {
                        items(wordsList) { word ->
                            ExpandableWordCard(
                                word = word,
                                onCardArrowClick = { viewModel.onCardArrowClicked(word.word) },
                                onSaveNewDefinition = { newDefinition ->
                                    viewModel.saveWord(
                                        Word(
                                            word = word.word,
                                            results = null,
                                            definition = newDefinition
                                        )
                                    )
                                },
                                expanded = expandedCardIds.value.contains(word.word),
                            )
                        }
                    }
                }
            },
            onError = {
                Text(text = if (it.isEmpty()) "Unexpected error" else it)
            }
        )
    }
}