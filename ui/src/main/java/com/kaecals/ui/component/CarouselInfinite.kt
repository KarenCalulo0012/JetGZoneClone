package com.kaecals.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kaecals.jetgzoneclone.ui.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CarouselInfinite(
    pageCount: Int,
    modifier: Modifier = Modifier,
    initialPage: Int = 0,
    delay: Long = 2000,
    content: @Composable PagerScope.(page: Int) -> Unit,
) {
    val max = Short.MAX_VALUE.toInt()
    val half = max / 2

    val pagerPositionIndex = initialPage + half - half % pageCount
    val pagerState = rememberPagerState(pageCount = { max }, initialPage = pagerPositionIndex)

    val scope = rememberCoroutineScope()

    var isUserInteracting by remember { mutableStateOf(false) }
    var lastInteractionTime by remember { mutableLongStateOf(System.currentTimeMillis()) }

    // Handle user interaction
    LaunchedEffect(pagerState.isScrollInProgress) {
        if (pagerState.isScrollInProgress) {
            isUserInteracting = true
            lastInteractionTime = System.currentTimeMillis()
        } else {
            isUserInteracting = false
        }
    }

    // Auto-scroll logic
    LaunchedEffect(key1 = isUserInteracting, key2 = lastInteractionTime) {
        if (!isUserInteracting) {
            scope.launch {
                delay(delay)
                if (!isUserInteracting && System.currentTimeMillis() - lastInteractionTime >= delay) {
                    if (pagerState.currentPage + 1 < pagerState.pageCount) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                    lastInteractionTime = System.currentTimeMillis()
                }
            }
        }
    }

    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center,
    ) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = pageCount > 1,
            modifier = modifier,
        ) { index ->
            val page = index % pageCount
            this.content(page)
        }

        Box(
            modifier =
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 2.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp),
        ) {
            DotsIndicator(
                totalDots = pageCount,
                selectedIndex = pagerState.currentPage % pageCount,
            )
        }
    }
}


@Composable
private fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    dotSpacing: Dp = 4.dp,
    selectedColor: Color = Color.White,
    unselectedColor: Color = Color.LightGray,
) {
    Row(
        modifier = modifier.padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(dotSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier =
                Modifier
                    .size(
                        width = if (index == selectedIndex) 16.dp else 4.dp,
                        height = 4.dp,
                    )
                    .background(
                        color = if (index == selectedIndex) selectedColor else unselectedColor,
                        shape = CircleShape,
                    ),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CarouselInfiniteSection(modifier: Modifier = Modifier) {
    val images = listOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background,
    )

    CarouselInfinite(
        pageCount = images.size,
        modifier = Modifier.wrapContentWidth(),
    ) { page ->
        Card(modifier = Modifier.fillMaxWidth(),) {
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "Page $page",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
            )
        }
    }
}