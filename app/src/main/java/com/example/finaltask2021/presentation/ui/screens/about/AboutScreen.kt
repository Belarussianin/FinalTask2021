package com.example.finaltask2021.presentation.ui.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun AboutScreen(
    aboutViewModel: AboutViewModel = getViewModel(),
    onNavigateClick: (String) -> Unit = {}
) {
    LazyColumn {
        item {
            with(LocalDensity.current) {
                Text(
                    text = aboutViewModel.aboutText,
                    fontSize = (24 * fontScale).sp,
                    letterSpacing = fontScale.sp,
                    lineHeight = (28 * fontScale).sp,
                    textAlign = TextAlign.Start
                )
                SelectionContainer {
                    Text(
                        text = "arseni2507@gmail.com",
                        fontSize = (24 * fontScale).sp,
                        letterSpacing = fontScale.sp,
                        lineHeight = (28 * fontScale).sp,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}