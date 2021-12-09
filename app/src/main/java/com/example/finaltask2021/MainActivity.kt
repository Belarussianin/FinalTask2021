package com.example.finaltask2021

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.finaltask2021.common.DataState
import com.example.finaltask2021.common.TAG
import com.example.finaltask2021.presentation.components.SettingToggleButton
import com.example.finaltask2021.presentation.navigation.MainNavDrawerContent
import com.example.finaltask2021.presentation.navigation.MainNavHost
import com.example.finaltask2021.presentation.navigation.MainNavTargets
import com.example.finaltask2021.presentation.ui.screens.home.HomeScreen
import com.example.finaltask2021.presentation.ui.theme.FinalTask2021Theme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

//App should support dark and light themes
//
//App should have settings screen.
//
//All ViewModels have to be covered by unit tests

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    @OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            val darkThemeSettingState = viewModel.isDarkTheme.collectAsState()
            val innerPadding = 16.dp

            val isDark = when (darkThemeSettingState.value) {
                is DataState.Error -> isSystemInDarkTheme()
                is DataState.Ready -> darkThemeSettingState.value.data
                is DataState.Loading -> null
            }

            val scaffoldState = rememberScaffoldState()
            val backDropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)

            when (isDark) {
                null -> {}
                else -> {
                    Log.e(TAG, "MainActivityContent: isDark: $isDark")
                    FinalTask2021Theme(
                        darkTheme = isDark,
                    ) {
                        MainActivityContent(
                            scaffoldState = scaffoldState,
                            backDropScaffoldState = backDropScaffoldState,
                            drawerContent = {
                                MainNavDrawerContent(innerPadding) { navigateTo ->
                                    scope.launch {
                                        navController.navigate(navigateTo) {
                                            popUpTo(navController.graph.startDestinationId)
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                        scaffoldState.drawerState.close()
                                    }
                                }
                            },
                            appBar = {
                                MainAppBar(
                                    title = {
                                        Text("Words app")
                                    },
                                    navigationIcon = {
                                        IconButton(
                                            onClick = {
                                                scope.launch {
                                                    if (scaffoldState.drawerState.isClosed) {
                                                        scaffoldState.drawerState.open()
                                                    } else {
                                                        scaffoldState.drawerState.close()
                                                    }
                                                }
                                            }
                                        ) {
                                            if (scaffoldState.drawerState.isClosed) {
                                                Icon(
                                                    Icons.Default.Menu,
                                                    contentDescription = "Menu"
                                                )
                                            } else {
                                                Icon(
                                                    Icons.Default.Close,
                                                    contentDescription = "Close"
                                                )
                                            }
                                        }
                                    }
                                )
                            },
                            backLayerContent = {
                                Box(Modifier.padding(innerPadding)) {
                                    SettingToggleButton(
                                        text = "Dark theme",
                                        isChecked = isDark,
                                        onClick = { isChecked ->
                                            viewModel.saveIsDarkTheme(isChecked)
                                        }
                                    )
                                }
                            },
                            frontLayerContent = {
                                Box(Modifier.padding(innerPadding)) {
                                    MainNavHost(
                                        navController = navController,
                                        startDestination = MainNavTargets.HomeScreen
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    @ExperimentalMaterialApi
    @Composable
    private fun MainActivityContent(
        scaffoldState: ScaffoldState,
        backDropScaffoldState: BackdropScaffoldState,
        drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
        appBar: @Composable () -> Unit,
        backLayerContent: @Composable () -> Unit,
        frontLayerContent: @Composable () -> Unit
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = drawerContent,
            content = {
                BackdropScaffold(
                    scaffoldState = backDropScaffoldState,
                    appBar = appBar,
                    backLayerContent = backLayerContent,
                    frontLayerContent = frontLayerContent
                )
            }
        )
    }
}

@Composable
fun MainAppBar(
    title: @Composable () -> Unit,
    navigationIcon: @Composable (() -> Unit)?
) {
    TopAppBar(
        title = title,
        navigationIcon = navigationIcon,
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Preview(showSystemUi = true, device = Devices.PIXEL_3A_XL)
@Composable
private fun DefaultPreview() {
    FinalTask2021Theme {
        HomeScreen()
    }
}