package com.example.finaltask2021.presentation.ui.screens.notes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.finaltask2021.common.Process
import com.example.finaltask2021.common.TAG
import com.example.finaltask2021.domain.model.Word
import com.example.finaltask2021.presentation.components.AddTextField
import com.example.finaltask2021.presentation.components.SearchTextField
import com.example.finaltask2021.presentation.components.words.WordCard
import com.example.finaltask2021.presentation.navigation.MainNavTargets
import com.example.finaltask2021.presentation.ui.screens.words.WordViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AddNoteScreen(
    viewModel: AddNoteViewModel = getViewModel(),
    onNavigateClick: (String) -> Unit = {}
) {
    val newWordTextFieldState = remember { mutableStateOf(TextFieldValue("")) }
    val newDefinitionTextFieldState = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddTextField(label = "Word", state = newWordTextFieldState)
        Spacer(modifier = Modifier.height(10.dp))
        AddTextField(label = "Definition", state = newDefinitionTextFieldState)

        Button(onClick = {
            viewModel.saveNewWord(
                word = newWordTextFieldState.value.text,
                definition = newDefinitionTextFieldState.value.text
            )
            newWordTextFieldState.value = newWordTextFieldState.value.copy("")
            newDefinitionTextFieldState.value = newDefinitionTextFieldState.value.copy("")
        }) {
            Text(text = "Enter")
        }
    }

    SideEffect {
        Log.e(TAG, "AddNoteScreen: Composed")
    }
}