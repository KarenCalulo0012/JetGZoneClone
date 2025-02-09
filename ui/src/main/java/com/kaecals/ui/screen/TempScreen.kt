package com.kaecals.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kaecals.ui.component.TextMarquee
import com.kaecals.ui.component.floatingContent.DraggableImage
import com.kaecals.ui.component.floatingContent.DraggableLayout
import com.kaecals.ui.section.CarouselScreen
import com.kaecals.ui.section.GameTabSection
import com.kaecals.ui.section.JackpotWinnerSection
import com.kaecals.viewmodel.WebViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    Column {
//        Greeting(name = "Android")
        CarouselScreen()
        TextMarquee(
            text = "Congratulations bi*** ***aj！the ₱ 20,666,999jack!! Please claim your price. Thank you!, " +
                    "Congratulations bi*** ***aj！the ₱ 20,666,999jack!! Please claim your price. Thank you!",
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )
        JackpotWinnerSection()
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