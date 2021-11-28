package com.example.finaltask2021.presentation.ui.about

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.finaltask2021.presentation.navigation.MainNavTarget
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun AboutScreen(
    aboutViewModel: AboutViewModel = getViewModel(),
    onNavigateClick: (String) -> Unit = {}
) {
    Column {
        Text(
            text = aboutViewModel.getAboutText(),
            textAlign = TextAlign.Center
        )
        Button(onClick = { onNavigateClick(MainNavTarget.HomeScreen.name) }) {
            Text(text = "To ${MainNavTarget.HomeScreen.name}")
        }
    }
}