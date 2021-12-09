package com.example.finaltask2021.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.finaltask2021.R

@Composable
fun MainNavDrawerContent(
    innerPadding: Dp,
    onNavigateClick: (String) -> Unit = {}
) {
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
            Image(painterResource(id = R.drawable.nav_drawer_image), "Dmitry")
            TabRowDefaults.Divider()
            // Drawer items
            Spacer(modifier = Modifier.height(innerPadding))
            Button(onClick = { onNavigateClick(MainNavTargets.HomeScreen.name) }) {
                Text(text = "To ${MainNavTargets.HomeScreen.name}")
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
}