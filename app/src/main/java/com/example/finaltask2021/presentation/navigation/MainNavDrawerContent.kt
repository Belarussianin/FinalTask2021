package com.example.finaltask2021.presentation.navigation

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.finaltask2021.R
import com.example.finaltask2021.common.TAG
import com.example.finaltask2021.presentation.components.ColumnDivider

@Composable
fun MainNavDrawerContent(
    innerPadding: Dp,
    onNavigateClick: (String) -> Unit = {}
) {
    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = innerPadding.value.dp,
                        top = innerPadding.value.dp * 2,
                        end = innerPadding.value.dp
                    )
            ) {
                item {
                    Row {
                        with(LocalDensity.current) {
                            Log.e(TAG, "MainNavDrawerContent: $density")
                            Image(
                                painterResource(id = R.drawable.nav_drawer_image), "Dmitry",
                                modifier = Modifier
                                    .sizeIn(
                                        minWidth = 100.dp,
                                        minHeight = 300.dp,
                                        maxWidth = 200.dp,
                                        maxHeight = 600.dp
                                    )
                                    .scale(1f)
                            )
                        }
                        ColumnDivider()
                        NavContent(
                            innerPadding = innerPadding,
                            onNavigateClick = onNavigateClick
                        )
                    }
                }
            }
        }
        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = innerPadding.value.dp,
                        top = innerPadding.value.dp * 2,
                        end = innerPadding.value.dp
                    )
            ) {
                item {
                    with(LocalDensity.current) {
                        Log.e(TAG, "MainNavDrawerContent: $density")
                        Image(
                            painterResource(id = R.drawable.nav_drawer_image), "Dmitry",
                            modifier = Modifier
                                .sizeIn(
                                    minWidth = 100.dp,
                                    minHeight = 300.dp,
                                    maxWidth = 200.dp,
                                    maxHeight = 600.dp
                                )
                                .scale(1f)
                        )
                    }
                    TabRowDefaults.Divider()
                    NavContent(
                        innerPadding = innerPadding,
                        onNavigateClick = onNavigateClick
                    )
                }
            }
        }
    }

}

@Composable
private fun NavContent(
    innerPadding: Dp,
    onNavigateClick: (String) -> Unit = {}
) {
    Column {
        // Drawer items
        Spacer(modifier = Modifier.height(innerPadding))
        Button(onClick = { onNavigateClick(MainNavTargets.HomeScreen.name) }) {
            Text(text = "To ${MainNavTargets.HomeScreen.name}")
        }
        Spacer(modifier = Modifier.height(innerPadding))
        Button(onClick = { onNavigateClick(MainNavTargets.AddNoteScreen.name) }) {
            Text(text = "To ${MainNavTargets.AddNoteScreen.name}")
        }
        Spacer(modifier = Modifier.height(innerPadding))
        Button(onClick = { onNavigateClick(MainNavTargets.WordScreen.name) }) {
            Text(text = "To ${MainNavTargets.WordScreen.name}")
        }
        Spacer(modifier = Modifier.height(innerPadding))
        Button(onClick = { onNavigateClick(MainNavTargets.DictionaryScreen.name) }) {
            Text(text = "To ${MainNavTargets.DictionaryScreen.name}")
        }
        Spacer(Modifier.height(innerPadding))
        Button(onClick = { onNavigateClick(MainNavTargets.AboutScreen.name) }) {
            Text(text = "To ${MainNavTargets.AboutScreen.name}")
        }
    }
}