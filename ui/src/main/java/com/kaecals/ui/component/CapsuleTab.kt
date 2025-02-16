package com.kaecals.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaecals.jetgzoneclone.ui.R
import com.kaecals.ui.screen.BlankScreen
import kotlinx.coroutines.launch

// Sealed class for different Tab content types
sealed class CapsuleTabItem {
    data class TextOnly(val text: String) : CapsuleTabItem()
    data class IconWithText(val icon: Int, val title: String) : CapsuleTabItem()
}

@Composable
private fun CapsuleTab(
    item: CapsuleTabItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier // Modifier passed from parent
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(40.dp)
            .padding(2.dp)
            .clickable(
                onClick = onClick,
                indication = null, // Remove ripple effect
                interactionSource = remember { MutableInteractionSource() }
            )
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        when (item) {
            is CapsuleTabItem.TextOnly -> {
                Text(
                    text = item.text,
                    fontSize = 14.sp,
                    color = if (isSelected) Color.White else Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
            }

            is CapsuleTabItem.IconWithText -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    IconImage(
                        icon = item.icon,
                        iconSize = 16.dp,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = item.title,
                        fontSize = 14.sp,
                        color = if (isSelected) Color.White else Color.Black
                    )
                }
            }
        }
    }
}


@Composable
fun CapsuleTabRow(
    modifier: Modifier = Modifier,
    items: List<CapsuleTabItem>,
    content: @Composable (Int) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = modifier
                .fillMaxWidth()  // Make the row take full width
                .clip(RoundedCornerShape(20.dp))
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth() // Ensure the row fills the width
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Gray,
                                Color.DarkGray
                            )
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .then(if (items.size > 5) Modifier.horizontalScroll(scrollState) else Modifier),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    CapsuleTab(
                        item = item,
                        isSelected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                            coroutineScope.launch {
                                if (items.size > 5) {
                                    if (index >= items.size - 5) {
                                        scrollState.animateScrollTo(scrollState.maxValue)
                                    } else if (index < 5) {
                                        scrollState.animateScrollTo(0)
                                    }
                                }
                            }
                        },
                        modifier = if (items.size <= 5) {
                            // Distribute the tab width equally across the available space
                            Modifier.weight(1f)
                        } else {
                            // Allow scrolling for more than 5 items
                            Modifier.wrapContentWidth()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Render content of the selected tab
        content(selectedTabIndex)
    }
}

@Preview
@Composable
private fun CapsulePreview() {
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
            1 -> BlankScreen("TEST1")
            2 -> BlankScreen("TEST2")
            3 -> BlankScreen("TEST3")
            4 -> BlankScreen("TEST4")
            5 -> BlankScreen("TEST5")
            // Add more cases if needed
        }
    }
}

@Preview
@Composable
private fun StringPreview() {
    val items = listOf(
        CapsuleTabItem.TextOnly("All Games"),
        CapsuleTabItem.TextOnly("100/200"),
        CapsuleTabItem.TextOnly("200/300"),
        CapsuleTabItem.TextOnly("300/400"),
        CapsuleTabItem.TextOnly("400/500"),
    )
    CapsuleTabRow(items = items) {
        when (it) {
            0 -> BlankScreen("TEST")
            1 -> BlankScreen("TEST1")
            2 -> BlankScreen("TEST2")
            3 -> BlankScreen("TEST3")
            4 -> BlankScreen("TEST4")
        }
    }
}

@Preview
@Composable
private fun IconWithText() {
    val items = listOf(
        CapsuleTabItem.IconWithText(R.drawable.ic_coin, "All Game"),
        CapsuleTabItem.IconWithText(R.drawable.ic_refresh, "Casino"),
        CapsuleTabItem.IconWithText(R.drawable.ic_logo, "Poker"),
        CapsuleTabItem.IconWithText(R.drawable.ic_coin, "Slot"),
        CapsuleTabItem.IconWithText(R.drawable.ic_refresh, "Tongits"),
        CapsuleTabItem.IconWithText(R.drawable.ic_refresh, "Tongits"),
    )
    CapsuleTabRow(items = items) {
        when (it) {
            0 -> BlankScreen("TEST")
            1 -> BlankScreen("TEST1")
            2 -> BlankScreen("TEST2")
            3 -> BlankScreen("TEST3")
            4 -> BlankScreen("TEST4")
            5 -> BlankScreen("TEST4")
        }
    }
}

@Preview
@Composable
private fun DualItems() {
    val items = listOf(
        CapsuleTabItem.TextOnly("All Games"),
        CapsuleTabItem.TextOnly("100/200"),
    )
    CapsuleTabRow(
        modifier = Modifier.fillMaxWidth(),
        items = items
    ) {
        when (it) {
            0 -> BlankScreen("TEST")
            1 -> BlankScreen("TEST1")
        }
    }
}
