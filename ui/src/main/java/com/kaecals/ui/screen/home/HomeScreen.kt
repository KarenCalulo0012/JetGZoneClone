package com.kaecals.ui.screen.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaecals.ui.model.defaultGameCategoryList
import com.kaecals.ui.screen.AccountScreen
import com.kaecals.ui.screen.PromoScreen
import com.kaecals.ui.screen.ScreenContent
import com.kaecals.ui.screen.WalletScreen
import com.kaecals.ui.section.GameTabSection

@Composable
fun HomeScreen() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { defaultGameCategoryList.size }
    )
    val lazyListState = rememberLazyListState()
    val tabRowHeight = 52.dp

    // Variables to track scroll direction and whether to hide/show the tab
    var isScrollingUp by remember { mutableStateOf(true) }
    var previousScrollOffset by remember { mutableIntStateOf(0) }
    var totalOffset by remember { mutableFloatStateOf(0f) }

    // Detect scroll direction and update immediately during scrolling
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemScrollOffset }
            .collect { scrollOffset ->
                isScrollingUp = scrollOffset < previousScrollOffset
                previousScrollOffset = scrollOffset

                // Calculate total offset: hide when scrolling down, show when scrolling up
                totalOffset = if (isScrollingUp) {
                    (totalOffset - 10f).coerceAtLeast(0f) // Scrolling up (show tab)
                } else {
                    (totalOffset + 10f).coerceAtMost(tabRowHeight.value) // Scrolling down (hide tab)
                }
            }
    }

    // Animate TabRow translation based on scroll direction
    val tabOffsetY by animateDpAsState(
        targetValue = -totalOffset.dp, // Move upwards to hide or downwards to show
        animationSpec = tween(durationMillis = 300)
    )
    Column {
//        Greeting(name = "Android")
        GameTabSection(pagerState = pagerState, modifier = Modifier.offset(y = tabOffsetY)) {
            println("Selected tab index: $it")
            when (it) {
                0 -> AllScreen()
                1 -> ScreenContent(lazyListState)
                2 -> AccountScreen()
                3 -> PromoScreen()
                4 -> WalletScreen()
                5 -> AccountScreen()
            }

        }
    }
}