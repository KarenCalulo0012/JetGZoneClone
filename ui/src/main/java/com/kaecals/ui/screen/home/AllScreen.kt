package com.kaecals.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kaecals.ui.component.TextMarquee
import com.kaecals.ui.component.floatingContent.DraggableImage
import com.kaecals.ui.component.floatingContent.DraggableLayout
import com.kaecals.ui.section.CarouselScreen
import com.kaecals.ui.section.JackpotWinnerSection

@Composable
fun AllScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalDivider(thickness = 8.dp, color = Color.Unspecified)
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