package com.example.finaltask2021.common

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.animation.core.snap
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.window.layout.WindowMetricsCalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

//searchResultState.value?.let { wordSearchState ->
//            when (wordSearchState.isLoading) {
//                true -> {
//                    CircularProgressIndicator()
//                }
//                false -> {
//                    wordSearchState.data?.let {
//                        WordCard(word = it)
//                    }
//                    if (wordSearchState.data == null) {
//                        Column {
//                            Text(wordSearchState.error)
//                            Button(onClick = { viewModel.onRightSwipe(Word("")) }) {
//                                Text("Refresh")
//                            }
//                        }
//                    }
//                }
//            }
//        }

@Composable
fun <T> UiState<T>.Process(
    onLoading: @Composable () -> Unit,
    onReady: @Composable (data: T) -> Unit,
    onError: @Composable (error: String) -> Unit
) where T : Any {
    when (isLoading) {
        true -> {
            onLoading()
        }
        false -> {
            data?.let {
                onReady(it)
            }
            if (data == null) {
                onError(error)
            }
        }
    }
}

//Left for best times
//@Composable
//fun Activity.windowSize(): DpSize {
//    val configuration = LocalConfiguration.current
//    val windowMetrics = remember(configuration) {
//        WindowMetricsCalculator.getOrCreate()
//            .computeCurrentWindowMetrics(this)
//    }
//    return with(LocalDensity.current) {
//        windowMetrics.bounds.toComposeRect().size.toDpSize()
//    }
//}
//
//@Composable
//fun Activity.rememberWindowSize() {
//    val configuration = LocalConfiguration.current
//    val windowMetrics = remember(configuration) {
//        WindowMetricsCalculator.getOrCreate()
//            .computeCurrentWindowMetrics(this)
//    }
//    val windowDpSize = with(LocalDensity.current) {
//        windowMetrics.bounds.toComposeRect().size.toDpSize()
//    }
//    val widthWindowSizeClass = when {
//        windowDpSize.width < 600.dp -> WindowSize.COMPACT
//        windowDpSize.width < 840.dp -> WindowSize.MEDIUM
//        else -> WindowSize.EXPANDED
//    }
//
//    val heightWindowSizeClass = when {
//        windowDpSize.height < 480.dp -> WindowSize.COMPACT
//        windowDpSize.height < 900.dp -> WindowSize.MEDIUM
//        else -> WindowSize.EXPANDED
//    }
//
//    // Use widthWindowSizeClass and heightWindowSizeClass
//}