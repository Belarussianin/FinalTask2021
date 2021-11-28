package com.example.finaltask2021.presentation.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.finaltask2021.common.TAG
import com.example.finaltask2021.common.swipeableLeftRight
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun SwipeableCard(
    elevation: Dp = 8.dp,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .swipeableLeftRight(onLeft = {
                onSwipeLeft()
                Log.e(TAG, "SwipeableCard: onLeft")
            }, onRight = {
                onSwipeRight()
                Log.e(TAG, "SwipeableCard: onRight")
            }),
        elevation = elevation,
        content = content
    )
}