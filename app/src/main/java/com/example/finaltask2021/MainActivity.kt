package com.example.finaltask2021

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finaltask2021.common.TAG
import com.example.finaltask2021.presentation.navigation.MainNavHost
import com.example.finaltask2021.presentation.navigation.MainNavigator
import com.example.finaltask2021.presentation.ui.main.HomeScreen
import com.example.finaltask2021.presentation.ui.theme.FinalTask2021Theme
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.android.get
import org.koin.androidx.compose.getViewModel

//App should support dark and light themes
//
//App should have settings screen.
//
//All ViewModels have to be covered by unit tests

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel = get()

    @InternalCoroutinesApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val mainNavigator = MainNavigator()
            FinalTask2021Theme {
                MainActivityContent(viewModel, navController, mainNavigator)
            }
        }
    }

    @ExperimentalMaterialApi
    @Composable
    private fun MainActivityContent(
        mainViewModel: MainViewModel = getViewModel(),
        navController: NavHostController = rememberNavController(),
        mainNavigator: MainNavigator
    ) {
        val scaffoldState = rememberBackdropScaffoldState(
            BackdropValue.Concealed
        )
        val scope = rememberCoroutineScope()
        val innerPadding = 16.dp

        Scaffold(
            drawerContent = {
                Text("Drawer title", modifier = Modifier.padding(innerPadding))
                Divider()
                // Drawer items
            },
            content = {
                BackdropScaffold(
                    scaffoldState = scaffoldState,
                    appBar = {
                        TopAppBar(
                            title = { Text("Hello Jetpack Compose!") },
                            navigationIcon = {
                                if (scaffoldState.isConcealed) {
                                    IconButton(
                                        onClick = {
                                            scope.launch { scaffoldState.reveal() }
                                        }
                                    ) {
                                        Icon(
                                            Icons.Default.Menu,
                                            contentDescription = "Menu"
                                        )
                                    }
                                } else {
                                    IconButton(
                                        onClick = {
                                            scope.launch { scaffoldState.conceal() }
                                        }
                                    ) {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = "Close"
                                        )
                                    }
                                }
                            },
                            backgroundColor = Color.Transparent,
                            elevation = 0.dp
                        )
                    },
                    backLayerContent = {
                        Box(Modifier.padding(innerPadding)) {
                            Text("Fast settings or something else, coming soon!")
                        }
                    },
                    frontLayerContent = {
                        Box(Modifier.padding(innerPadding)) {
                            MainNavHost(navController = navController, mainNavigator = mainNavigator)
                        }
                    }
                )
            }
        )
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true, device = Devices.PIXEL_3A_XL)
@Composable
private fun DefaultPreview() {
    FinalTask2021Theme {
        HomeScreen()
    }
}