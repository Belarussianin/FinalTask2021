package com.example.finaltask2021.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Shapes
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.finaltask2021.common.SwipeDirection
import com.example.finaltask2021.common.TAG
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun SwipeableCard(
    elevation: Dp = 8.dp,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    content: @Composable () -> Unit
) {
    Row {
        Column(
            Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color.Green, RoundedCornerShape(5.dp))
        ) {}
        Column(
            Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color.Red, RoundedCornerShape(5.dp))
        ) {}
    }
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val swipeableState = rememberSwipeableState(SwipeDirection.Initial)
        if (swipeableState.isAnimationRunning) {
            DisposableEffect(Unit) {
                onDispose {
                    when (swipeableState.currentValue) {
                        SwipeDirection.Left -> {
                            onSwipeLeft()
                            Log.e(TAG, "Card swiped Left")
                        }
                        SwipeDirection.Right -> {
                            onSwipeRight()
                            Log.e(TAG, "Card swiped Right")
                        }
                        else -> {
                            return@onDispose
                        }
                    }
                }
            }
        }
        val sizePx = with(LocalDensity.current) { (maxWidth.toPx() / 1.5).toFloat() }
        val anchors =
            mapOf(
                -sizePx to SwipeDirection.Left,
                0f to SwipeDirection.Initial,
                sizePx to SwipeDirection.Right
            ) // Maps anchor points (in px) to states

        Card(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ ->
                        FractionalThreshold(0.5f)
                    },
                    orientation = Orientation.Horizontal
                )
                .offset {
                    if (swipeableState.offset.value.roundToInt() in -500..500) {
                        IntOffset(swipeableState.offset.value.roundToInt(), 0)
                    } else {
                        IntOffset(
                            if (swipeableState.offset.value.roundToInt() > 0) 500 else -500,
                            0
                        )
                    }
                },
            elevation = elevation,
            content = content
        )
    }
}