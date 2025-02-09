package com.kaecals.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kaecals.ui.component.VideoPlayer
import com.kaecals.ui.component.floatingContent.DraggableImage
import com.kaecals.ui.component.floatingContent.DraggableLayout

@Composable
fun MoreScreen() {
    Text(text = "More Screen")
}

@Composable
fun HomeScreen() {
    Column {
        Greeting(name = "Android")
        VideoPlayer(
            "https://gamezone.ph/video/valentine-activity.mp4",
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp))
        )
        DraggableLayout(draggableContent = { width, height -> DraggableImage(width, height) })
    }
}

@Composable
fun PromoScreen() {
    Text(text = "Promo Screen")
}

@Composable
fun WalletScreen() {
    Text(text = "Wallet Screen")
}

@Composable
fun AccountScreen() {
    Text(text = "Account Screen")
}