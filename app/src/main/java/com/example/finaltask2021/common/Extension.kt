package com.example.finaltask2021.common

import android.app.Activity
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
import androidx.window.layout.WindowMetricsCalculator
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
fun Modifier.swipeableLeftRight(onLeft: () -> Unit, onRight: () -> Unit): Modifier = composed {
    var width by rememberSaveable { mutableStateOf(0f) }
    val swipeableState = rememberSwipeableState(
        SwipeDirection.Initial,
        animationSpec = snap()
    )
    val anchorWidth = remember(width) {
        if (width == 0f) {
            1f
        } else {
            width
        }
    }
    val scope = rememberCoroutineScope()
    if (swipeableState.isAnimationRunning) {
        DisposableEffect(Unit) {
            onDispose {
                when (swipeableState.currentValue) {
                    SwipeDirection.Left -> {
                        onLeft()
                    }
                    SwipeDirection.Right -> {
                        onRight()
                    }
                    else -> {
                        return@onDispose
                    }
                }
                scope.launch {
                    swipeableState.snapTo(SwipeDirection.Initial)
                }
            }
        }
    }
    return@composed Modifier
        .onSizeChanged { width = it.width.toFloat() }
        .swipeable(
            state = swipeableState,
            anchors = mapOf(
                0f to SwipeDirection.Left,
                anchorWidth / 2 to SwipeDirection.Initial,
                anchorWidth to SwipeDirection.Right,
            ),
            thresholds = { _, _ -> FractionalThreshold(0.3f) },
            orientation = Orientation.Horizontal
        )
}

@Composable
fun Activity.windowSize(): DpSize {
    val configuration = LocalConfiguration.current
    val windowMetrics = remember(configuration) {
        WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)
    }
    return with(LocalDensity.current) {
        windowMetrics.bounds.toComposeRect().size.toDpSize()
    }
}

@Composable
fun Activity.rememberWindowSizeClass() {
    val configuration = LocalConfiguration.current
    val windowMetrics = remember(configuration) {
        WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)
    }
    val windowDpSize = with(LocalDensity.current) {
        windowMetrics.bounds.toComposeRect().size.toDpSize()
    }
    val widthWindowSizeClass = when {
        windowDpSize.width < 600.dp -> WindowSize.COMPACT
        windowDpSize.width < 840.dp -> WindowSize.MEDIUM
        else -> WindowSize.EXPANDED
    }

    val heightWindowSizeClass = when {
        windowDpSize.height < 480.dp -> WindowSize.COMPACT
        windowDpSize.height < 900.dp -> WindowSize.MEDIUM
        else -> WindowSize.EXPANDED
    }

    // Use widthWindowSizeClass and heightWindowSizeClass
}