package com.kaecals.ui.component


import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun rememberGelatinAnimation(selected: Boolean): Animatable<Float, *> {
    // Create a Y-axis scale animation state
    val scaleY = remember { Animatable(1f) }

    // Trigger the animation when the selected state changes
    LaunchedEffect(selected) {
        if (selected) {
            // Squeeze
            scaleY.animateTo(
                targetValue = 0.7f, // Squeeze vertically
                animationSpec = tween(durationMillis = 200, easing = LinearEasing)
            )
            // Bounce back
            scaleY.animateTo(
                targetValue = 1.3f, // Stretch and bounce back
                animationSpec = tween(durationMillis = 150, easing = LinearEasing)
            )
            scaleY.animateTo(
                targetValue = 0.8f, // Squeeze vertically
                animationSpec = tween(durationMillis = 150, easing = LinearEasing)
            )
            // Bounce back
            scaleY.animateTo(
                targetValue = 1.1f, // Stretch and bounce back
                animationSpec = tween(durationMillis = 150, easing = LinearEasing)
            )
            // Settle to normal
            scaleY.animateTo(
                targetValue = 1.0f, // Return to normal size
                animationSpec = tween(durationMillis = 100, easing = LinearEasing)
            )
        }
    }

    return scaleY
}

fun enterSlideInVertically(): EnterTransition {
    return slideInVertically(
        initialOffsetY = { fullHeight -> fullHeight },
        animationSpec = tween(1000)
    ) + fadeIn()
}

fun exitSlideOutVertically(): ExitTransition {
    return slideOutVertically(
        targetOffsetY = { fullHeight -> fullHeight },
        animationSpec = tween(2000)
    ) + fadeOut()
}