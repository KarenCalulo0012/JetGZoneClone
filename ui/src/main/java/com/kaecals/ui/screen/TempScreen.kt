package com.kaecals.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kaecals.jetgzoneclone.ui.R
import com.kaecals.ui.component.CapsuleTabItem
import com.kaecals.ui.component.CapsuleTabRow
import com.kaecals.ui.screen.home.HomeScreen
import com.kaecals.viewmodel.WebViewModel
import org.koin.androidx.compose.koinViewModel

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
    val items = listOf(
        CapsuleTabItem.TextOnly("All Games"),
        CapsuleTabItem.TextOnly("50/100"),
        CapsuleTabItem.IconWithText(R.drawable.ic_coin, "Home"),
        CapsuleTabItem.TextOnly("100/200"),
        CapsuleTabItem.IconWithText(R.drawable.ic_refresh, "Favorites"),
    )
    CapsuleTabRow(items = items) {
        when (it) {
            0 -> BlankScreen("TEST")
            1 -> HomeScreen()
            2 -> PromoScreen()
            3 -> BlankScreen("TEST3")
            4 -> BlankScreen("TEST4")
            5 -> BlankScreen("TEST5")
            // Add more cases if needed
        }
    }
}

@Composable
fun AccountScreen() {
    Text(text = "Account Screen")
}

@Composable
fun ScreenContent(lazyListState: LazyListState = rememberLazyListState()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(state = lazyListState) {
            items(100) {
                Text(text = "Item $it", modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun BlankScreen(text: String = "") {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "$text Screen")
    }
}