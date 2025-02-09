package com.kaecals.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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