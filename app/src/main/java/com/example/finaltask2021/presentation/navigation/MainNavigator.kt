package com.example.finaltask2021.presentation.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainNavigator {

    private val _sharedFlow =
        MutableSharedFlow<MainNavTarget>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(mainNavTarget: MainNavTarget) {
        _sharedFlow.tryEmit(mainNavTarget)
    }
}