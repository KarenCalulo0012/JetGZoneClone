package com.kaecals.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.kaecals.ui.component.CarouselInfiniteSection
import com.kaecals.ui.component.VideoPlayer

@Composable
fun CarouselScreen() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp * 0.1f
    Row {
        Box(
            modifier = Modifier
                .size(screenHeight)
                .weight(0.45f)
                .padding(start = 24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray),
        ) {
            VideoPlayer(
                "https://gamezone.ph/video/valentine-activity.mp4",
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp))
            )
        }
        Box(
            modifier = Modifier
                .height(screenHeight)
                .weight(1f)
                .padding(start = 4.dp, end = 24.dp)
        ) {
            CarouselInfiniteSection()
        }
    }
}