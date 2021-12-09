package com.example.finaltask2021.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finaltask2021.presentation.ui.screens.about.AboutScreen
import com.example.finaltask2021.presentation.ui.screens.dictionary.DictionaryScreen
import com.example.finaltask2021.presentation.ui.screens.home.HomeScreen
import com.example.finaltask2021.presentation.ui.screens.words.WordScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: MainNavTargets,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = startDestination.name,
        modifier = modifier
    ) {
        composable(MainNavTargets.HomeScreen.name) {
            HomeScreen { navigateTo ->
                scope.launch {
                    navController.navigate(navigateTo)
                }
            }
        }
        composable(MainNavTargets.WordScreen.name) {
            WordScreen { navigateTo ->
                scope.launch {
                    navController.navigate(navigateTo)
                }
            }
        }
        composable(MainNavTargets.DictionaryScreen.name) {
            DictionaryScreen { navigateTo ->
                scope.launch {
                    navController.navigate(navigateTo)
                }
            }
        }
        composable(MainNavTargets.AboutScreen.name) {
            AboutScreen { navigateTo ->
                scope.launch {
                    navController.navigate(navigateTo)
                }
            }
        }
    }
}