package com.example.finaltask2021.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finaltask2021.presentation.ui.about.AboutScreen
import com.example.finaltask2021.presentation.ui.main.HomeScreen
import com.example.finaltask2021.presentation.ui.words.WordScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalMaterialApi
@Composable
fun MainNavHost(
    navController: NavHostController,
    mainNavigator: MainNavigator,
    modifier: Modifier = Modifier
) {
    LaunchedEffect("navigation") {
        mainNavigator.sharedFlow.onEach {
            navController.navigate(it.name)
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = MainNavTarget.HomeScreen.name,
        modifier = modifier
    ) {
        composable(MainNavTarget.HomeScreen.name) {
            HomeScreen { navigateTo ->
                mainNavigator.navigateTo(MainNavTarget.valueOf(navigateTo))
            }
        }
        composable(MainNavTarget.WordScreen.name) {
            WordScreen { navigateTo ->
                mainNavigator.navigateTo(MainNavTarget.valueOf(navigateTo))
            }
        }
        composable(MainNavTarget.AboutScreen.name) {
            AboutScreen { navigateTo ->
                mainNavigator.navigateTo(MainNavTarget.valueOf(navigateTo))
            }
        }
    }
}