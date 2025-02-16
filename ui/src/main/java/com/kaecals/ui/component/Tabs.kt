package com.kaecals.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaecals.jetgzoneclone.ui.R
import com.kaecals.ui.screen.BlankScreen
import kotlinx.coroutines.launch

@Composable
fun RowScope.WalletTab(
    text: String,
    backgroundImage: Int,
    isSelected: Boolean,
    weight: Float,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .weight(weight)
            .height(60.dp)
            .clickable(
                onClick = onClick,
                indication = null, // Remove ripple effect
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Image(
                painter = painterResource(backgroundImage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )

            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }

}

@Preview
@Composable
fun CustomTabRow() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Deposit", "Withdraw")
    val backgroundImages = listOf(
        R.drawable.slanted_rectangle, // Replace with your drawable resource
        R.drawable.slanted_rectangle_1, // Replace with your drawable resource
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(16.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.DarkGray,
                                Color.Gray
                            )
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = tabs[0],
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                    Text(
                        text = tabs[1],
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                tabs.forEachIndexed { index, tab ->
                    val targetWeight = if (selectedTabIndex == index) 1f else 0.5f
                    val weight by animateFloatAsState(targetValue = targetWeight)
                    WalletTab(
                        text = tab,
                        backgroundImage = backgroundImages[index],
                        isSelected = selectedTabIndex == index,
                        weight = weight,
                        onClick = { selectedTabIndex = index }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (selectedTabIndex) {
            0 -> BlankScreen("Deposit")
            1 -> BlankScreen("Withdraw")
        }
    }
}

@Composable
fun CapsuleTab(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentWidth()
            .height(40.dp)
            .clickable(
                onClick = onClick,
                indication = null, // Remove ripple effect
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(2.dp)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (isSelected) Color.White else Color.Black,
            modifier = Modifier.padding(8.dp) // Add padding to the text
        )
    }
}


@Composable
fun CapsuleTabRow(items: List<String>) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        Box(modifier = Modifier.wrapContentWidth().clip(RoundedCornerShape(20.dp))) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Gray,
                                Color.DarkGray
                            )
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .horizontalScroll(scrollState),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    CapsuleTab(
                        text = item,
                        isSelected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                            if (index > 4) {
                                coroutineScope.launch {
                                    scrollState.animateScrollTo(
                                        index * (scrollState.maxValue / items.size)
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (selectedTabIndex) {
            0 -> TabContentScreen(item = items[0])
            1 -> TabContentScreen(item = items[1])
            2 -> TabContentScreen(item = items[2])
            3 -> TabContentScreen(item = items[3])
            4 -> TabContentScreen(item = items[4])
            5 -> TabContentScreen(item = items[5])
            // Add more cases if needed
        }
    }
}

@Composable
fun TabContentScreen(item: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$item Screen")
    }
}

@Preview
@Composable
private fun CapsulePreview() {
    val items = listOf("All Games", "50/100", "200/300", "100/200", "300/500", "More1")
    CapsuleTabRow(items = items)
}