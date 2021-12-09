package com.example.finaltask2021.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTextField(
    state: MutableState<TextFieldValue>,
    onChange: (String) -> Unit = {},
    onEnter: (String) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = state.value,
        onValueChange = {
            state.value = it.copy(it.text.replace("\n", ""))
            onChange(state.value.text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        label = {
            Text("Search: ${state.value.text}")
        },
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            keyboardManager?.hide()
            focusManager.moveFocus(FocusDirection.Next)
            onEnter(state.value.text)
        }),
        singleLine = true,
        maxLines = 1
    )
}