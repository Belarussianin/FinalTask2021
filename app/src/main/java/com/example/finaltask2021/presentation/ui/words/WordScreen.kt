package com.example.finaltask2021.presentation.ui.words

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.finaltask2021.common.TAG
import com.example.finaltask2021.presentation.navigation.MainNavTarget
import com.example.finaltask2021.presentation.ui.words.components.WordCard
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.getViewModel

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun WordScreen(
    viewModel: WordViewModel = getViewModel(),
    onNavigateClick: (String) -> Unit = {}
) {
    val word = viewModel.wordState.collectAsState()

    Column {
        Button(onClick = { onNavigateClick(MainNavTarget.HomeScreen.name) }) {
            Text(text = "To ${MainNavTarget.HomeScreen.name}")
        }
        Box(Modifier.fillMaxSize()) {
            when (word.value.isLoading) {
                true -> {
                    Text("Loading")
                }
                false -> {
                    Log.e(TAG, "WordScreen: ${word.value.word} ${word.value.error}")
                    word.value.word?.let { WordCard(it, {}, {}) }
                    word.value.word ?: Text(word.value.error)
                }
            }
        }
    }
}