package com.example.finaltask2021.presentation.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
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
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.presentation.components.SearchTextField
import com.example.finaltask2021.presentation.components.words.WordCard
import com.example.finaltask2021.presentation.ui.screens.words.WordViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.getViewModel

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    viewModel: WordViewModel = getViewModel(),
    onNavigateClick: (String) -> Unit = {}
) {
    val searchTextFieldState = remember { mutableStateOf(TextFieldValue("")) }
    val searchResultState = viewModel.wordState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchTextField(
            state = searchTextFieldState
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    searchResultState.value.data?.let { word -> viewModel.saveWord(word) }
                },
                content = {
                    Text(text = "Save")
                }
            )
            Button(
                onClick = {
                    viewModel.getWord(searchTextFieldState.value.annotatedString.text)
                },
                content = {
                    Text(text = "Enter")
                }
            )
        }
        searchResultState.value.Process(
            onLoading = {
                CircularProgressIndicator()
            },
            onReady = {
                WordCard(word = it)
            },
            onError = {
                Column {
                    Text(searchResultState.value.error)
                    Button(onClick = { viewModel.onRightSwipe(Word("")) }) {
                        Text("Refresh")
                    }
                }
            }
        )
    }

    SideEffect {
        Log.e(TAG, "HomeScreen: Composed")
    }
}