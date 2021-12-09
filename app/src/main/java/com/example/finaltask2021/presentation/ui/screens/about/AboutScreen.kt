package com.example.finaltask2021.presentation.ui.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun AboutScreen(
    aboutViewModel: AboutViewModel = getViewModel(),
    onNavigateClick: (String) -> Unit = {}
) {
    Column {
        Text(
            text = aboutViewModel.aboutText,
            textAlign = TextAlign.Center
        )
    }
}