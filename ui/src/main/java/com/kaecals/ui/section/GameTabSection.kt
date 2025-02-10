package com.kaecals.ui.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaecals.jetgzoneclone.ui.R
import com.kaecals.ui.model.GameCategoryModel
import com.kaecals.ui.model.defaultGameCategoryList
import com.kaecals.utils.noRippleTheme
import kotlinx.coroutines.launch

@Preview(showBackground = false)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTabSection(
    modifier: Modifier = Modifier,
    items: List<GameCategoryModel> = defaultGameCategoryList,
    pagerState: PagerState = rememberPagerState(initialPage = 0, pageCount = { items.size }),
    content: @Composable (Int) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    CompositionLocalProvider(LocalRippleConfiguration provides noRippleTheme) {
        TabRow(
            modifier = modifier.fillMaxWidth().height(52.dp),
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .padding(horizontal = 24.dp)
                        .offset(x = 0.dp, y = -(16).dp),
                )
            }
        ) {
            items.forEachIndexed { index, item ->
                val selected = pagerState.currentPage == index
                Tab(
                    modifier = Modifier.wrapContentHeight().padding(vertical = 12.dp)
                        .background(color = Color.DarkGray),
                    selected = selected,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    text = {
                        Column(
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(0.dp)
                        ) {
                            Box {
                                if (selected) {
                                    Image(
                                        painter = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(28.dp)  // Adjusted size
                                            .clip(CircleShape)
                                    )
                                }
                                Image(
                                    painter = painterResource(item.gameCategoryImage),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp) // Adjusted size
                                )
                            }
                            Text(
                                text = item.gameCategoryName,
                                fontSize = 9.sp
                            ) // Adjusted font size
                        }
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
    HorizontalPager(
        beyondViewportPageCount = items.size,
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
        userScrollEnabled = true,
        pageContent = { page ->
            content(page)
        }
    )
}