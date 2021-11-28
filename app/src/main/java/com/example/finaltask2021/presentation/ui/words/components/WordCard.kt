package com.example.finaltask2021.presentation.ui.words.components

import android.annotation.SuppressLint
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.presentation.components.SwipeableCard

@ExperimentalMaterialApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun WordCard(
    word: Word,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit
) {
    SwipeableCard(
        onSwipeLeft = onSwipeLeft,
        onSwipeRight = onSwipeRight,
        content = {
            Text(word.word)
        }
    )
}