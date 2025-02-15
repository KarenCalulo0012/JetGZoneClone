package com.kaecals.ui.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun AuthScreen(
    visible: Boolean,
    onClose: () -> Unit,
) {
    var contentVisible by remember { mutableStateOf(false) } // Content animation control


    // Delayed content animation trigger (Starts after screen animation completes)
    LaunchedEffect(visible) {
        delay(200)
        contentVisible = visible
    }

    // Content animation with a delay of 1.2 seconds
    val contentTransition = updateTransition(contentVisible, label = "Content Transition")

    // Content offset and alpha animation
    val contentOffsetY by contentTransition.animateDp(
        transitionSpec = { tween(durationMillis = 800, easing = FastOutSlowInEasing) },
        label = "Content Offset Y",
        targetValueByState = { if (it) 0.dp else 50.dp }
    )
    val contentAlpha by contentTransition.animateFloat(
        transitionSpec = { tween(durationMillis = 800, easing = LinearEasing) },
        label = "Content Alpha",
        targetValueByState = { if (it) 1f else 0f }
    )

    // Main layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.Transparent)
            .offset(y = contentOffsetY)
            .alpha(contentAlpha),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Fullscreen modal with animation
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            // Text and Button with synchronized animations
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = contentOffsetY)
                    .alpha(contentAlpha)
            ) {
                Text(
                    text = "Authentication Screen",
                    fontSize = 24.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    contentVisible = false // Hide content first
                    onClose()
                }) {
                    Text("Close")
                }
            }
        }
    }
}