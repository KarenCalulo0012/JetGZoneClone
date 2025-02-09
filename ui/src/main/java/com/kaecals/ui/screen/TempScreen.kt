package com.kaecals.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kaecals.ui.component.VideoPlayer
import com.kaecals.ui.component.floatingContent.DraggableImage
import com.kaecals.ui.component.floatingContent.DraggableLayout
import com.kaecals.viewmodel.WebViewModel
import org.koin.androidx.compose.koinViewModel

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
    val viewModel: WebViewModel = koinViewModel()
    val webView = viewModel.getWebView()
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { webView },
            modifier = Modifier.fillMaxSize(),
            update = { viewModel.loadUrl("https://gzone.ph/promotion/promo") }
        )
    }
}

@Composable
fun WalletScreen() {
    Text(text = "Wallet Screen")
}

@Composable
fun AccountScreen() {
    Text(text = "Account Screen")
}