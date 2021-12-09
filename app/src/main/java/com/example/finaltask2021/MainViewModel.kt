package com.example.finaltask2021

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaltask2021.common.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(private val appSettings: AppSettings) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow<DataState<Boolean?>>(DataState.Loading())
    val isDarkTheme: StateFlow<DataState<Boolean?>> get() = _isDarkTheme

    init {
        getDarkThemeSetting()
    }

    private fun getDarkThemeSetting() {
        appSettings.getIsDarkTheme.onEach { setting ->
            if (setting == null) {
                _isDarkTheme.value = DataState.Error("No saved setting \"isDarkTheme\"")
            } else {
                _isDarkTheme.value = DataState.Ready(setting)
            }
        }.launchIn(viewModelScope)
    }

    fun saveIsDarkTheme(darkTheme: Boolean) {
        viewModelScope.launch {
            appSettings.saveIsDarkTheme(darkTheme)
        }
    }
}