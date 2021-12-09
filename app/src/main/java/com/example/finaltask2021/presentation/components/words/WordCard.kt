package com.example.finaltask2021.presentation.components.words

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finaltask2021.domain.model.Word

@ExperimentalMaterialApi
@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    word: Word
) {
    Card(
        modifier = modifier.fillMaxSize(),
        elevation = 8.dp,
        content = {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier.verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SelectionContainer {
                    Text(
                        "Word: ${word.word}",
                        fontSize = with(LocalDensity.current) { (40 * fontScale).sp })
                }
                word.results?.let { results ->
                    for (result in results) {
                        result.definition?.let {
                            Text(
                                "Definition: ${result.definition}",
                                fontSize = with(LocalDensity.current) { (30 * fontScale).sp })
                        }
                    }
                }
            }
        }
    )
}