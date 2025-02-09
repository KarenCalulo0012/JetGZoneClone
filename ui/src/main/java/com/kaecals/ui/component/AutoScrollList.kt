package com.kaecals.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.delay

/**
 *  A composable function that creates an infinite auto-scrolling list with specified items and let you customize items
 *
 * @param items The list of items to be displayed in the auto-scrolling list.
 * @param batchCount The number of items to scroll by at each transition. Default is 2.
 * @param transitionTime The time in milliseconds between each auto-scroll. Default is 2000. (2 seconds)
 * @param modifier The modifier to be applied to the LazyColumn.
 * @param content A composable function that defines the content to be displayed for each item.
 */

@Composable
fun <T> AutoScrollList(
    items: List<T>,
    batchCount: Int = 2,
    transitionTime: Long = 2000,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit,
) {
    /**
     * first index will be the half of the Int.MAX_VALUE but will show the first item of the list
     */
    val listState = rememberLazyListState(Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2) % items.size)
    var itemSize by remember { mutableStateOf(IntSize.Zero) }
    var continueScrolling by remember { mutableStateOf(true) }
    val density = LocalDensity.current

    /**
     *Used to auto scrolling the list depends to the desired batchCount
     */
    LaunchedEffect(Unit) {
        while (true) {
            delay(transitionTime)
            if (continueScrolling) {
                val firstVisibleItemIndex = listState.firstVisibleItemIndex
                val nextIndex = firstVisibleItemIndex + batchCount
                listState.animateScrollToItem(nextIndex)
            }
        }
    }
    LaunchedEffect(listState.isScrollInProgress) {
        continueScrolling = !listState.isScrollInProgress
    }

    /**
     * Will display the number of items depends to the batch count
     */
    LazyColumn(
        state = listState,
        modifier =
        modifier
            .fillMaxWidth()
            .height(with(density) { (itemSize.height * batchCount).toDp() }),
    ) {
        items(Int.MAX_VALUE, itemContent = {
            val index = it % items.size
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinate ->
                        itemSize = coordinate.size
                    },
            ) {
                content(items[index])
            }
        })
    }
}
