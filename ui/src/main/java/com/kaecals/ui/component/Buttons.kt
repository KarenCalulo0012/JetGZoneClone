package com.kaecals.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(color = Color.Transparent, shape = RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = "Button",
            contentScale = ContentScale.Crop,
            modifier = Modifier.wrapContentSize()
        )
        content()
    }
}

@Preview
@Composable
fun IconRoundedCornerBackground(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Search,
    hasNotification: Boolean = false,
    onIconClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(36.dp)
            .background(color = Color.White.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(6.dp)
                .clickable { onIconClick() }
        )
        if (hasNotification) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(color = Color.Red, shape = CircleShape)
                    .align(Alignment.TopEnd)
            )
        }
    }

}