package com.example.finaltask2021.presentation.components.words

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.presentation.components.SwipeableCard

@ExperimentalMaterialApi
@Composable
fun SwipableWordCard(
    word: Word,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit
) {
    SwipeableCard(
        onSwipeLeft = onSwipeLeft,
        onSwipeRight = onSwipeRight,
        content = { WordCard(word = word) }
    )
}