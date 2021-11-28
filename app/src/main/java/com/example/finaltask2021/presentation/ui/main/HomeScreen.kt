package com.example.finaltask2021.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.finaltask2021.presentation.navigation.MainNavTarget

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    onNavigateClick: (String) -> Unit = {}
) {
    Column {
        Text(text = "Dmitry molodec", textAlign = TextAlign.Center)

        Button(onClick = { onNavigateClick(MainNavTarget.AboutScreen.name) }) {
            Text(text = "To ${MainNavTarget.AboutScreen.name}")
        }

        Button(onClick = { onNavigateClick(MainNavTarget.WordScreen.name) }) {
            Text(text = "To ${MainNavTarget.WordScreen.name}")
        }
    }
}