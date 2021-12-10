package com.example.finaltask2021.presentation.ui.screens.words

import android.util.Log
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.finaltask2021.common.Process
import com.example.finaltask2021.common.TAG
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.presentation.components.words.SwipableWordCard
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.getViewModel

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun WordScreen(
    viewModel: WordViewModel = getViewModel(),
    onNavigateClick: (String) -> Unit = {}
) {
    val wordState = viewModel.wordState.collectAsState()

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        wordState.value.Process(
            onLoading = {
                CircularProgressIndicator()
            },
            onReady = {
                Log.e(TAG, "WordScreen: ${wordState.value.data} ${wordState.value.error}")
                SwipableWordCard(it, {
                    viewModel.onLeftSwipe()
                }, {
                    viewModel.onRightSwipe(it)
                })
            },
            onError = {
                Column {
                    Text(wordState.value.error)
                    Button(onClick = { viewModel.onRightSwipe(Word("")) }) {
                        Text("Refresh")
                    }
                }
            }
        )
    }
}