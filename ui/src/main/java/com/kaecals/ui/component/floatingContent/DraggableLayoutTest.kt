package com.kaecals.ui.component.floatingContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaecals.jetgzoneclone.ui.R

/**
 * A preview composable function that showcases the usage of DraggableLayout.
 * The composable items provided in the `draggableContents` list will be positioned within the parent container.
 * The items are rendered in the order they appear in the list, with the last called item appearing on top.
 */
@Preview
@Composable
fun PreviewDraggableLayoutItems() {
    DraggableLayout(
        draggableContents =
            listOf<@Composable (Int, Int) -> Unit>(
                { parentWidth, parentHeight -> DraggableColumn(parentWidth, parentHeight) },
                { parentWidth, parentHeight -> DraggableBox(parentWidth, parentHeight, isDocked = false) },
                { parentWidth, parentHeight -> DraggableBox(parentWidth, parentHeight) },
                { parentWidth, parentHeight -> DraggableImage(parentWidth, parentHeight) },
            ),
    )
}

/**
 * A preview composable function that showcases the usage of DraggableLayout.
 * This example includes a single draggable item represented by DraggableColumn.
 * The item is rendered within the parent container.
 *
 */
@Preview
@Composable
fun PreviewDraggableLayout() {
    DraggableLayout(
        draggableContent = { parentWidth, parentHeight -> DraggableImage(parentWidth, parentHeight) },
    )
}

/**
 * A composable function representing a draggable Column.
 * It maintains state variables for offsets and container size, and applies draggable modifiers.
 *
 * @param parentWidth The width of the parent container.
 * @param parentHeight The height of the parent container.
 * @param isDocked Whether the item should dock to the left or right based on its final position after dragging.
 */
@Composable
fun DraggableColumn(
    parentWidth: Int,
    parentHeight: Int,
    isDocked: Boolean = true,
) {
    // State variables for offset and visibility
    val offsetX = remember { mutableFloatStateOf(0f) }
    val offsetY = remember { mutableFloatStateOf(0f) }

    // State variables for container size
    val itemContainerHeight = remember { mutableFloatStateOf(0f) }
    val itemContainerWidth = remember { mutableFloatStateOf(0f) }

    // Convert dp to pixels using LocalDensity
    val padding = with(LocalDensity.current) { 0.dp.toPx() }

    // Initialize offsets with padding if padding is greater than 0
    if (padding > 0f) {
        offsetX.floatValue = padding
        offsetY.floatValue = padding
    }

    // Container for the draggable item.
    Column(
        modifier =
            Modifier
                .wrapContentSize()
                .draggableOffset(offsetX, offsetY)
                .draggablePointerInput(
                    offsetX = offsetX,
                    offsetY = offsetY,
                    parentWidth = parentWidth,
                    parentHeight = parentHeight,
                    itemContainerHeight = itemContainerHeight.floatValue,
                    itemContainerWidth = itemContainerWidth.floatValue,
                    padding = padding,
                    isDocked = isDocked,
                )
                .onGloballyPositioned { coordinates ->
                    itemContainerWidth.floatValue = coordinates.size.width.toFloat()
                    itemContainerHeight.floatValue = coordinates.size.height.toFloat()
                },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Sample Image",
            contentScale = ContentScale.FillHeight,
            modifier =
                Modifier
                    .size(height = 70.dp, width = 60.dp),
        )
        Text(
            text = "Column",
            color = Color.Black,
            fontSize = 9.sp,
            textAlign = TextAlign.Center,
            modifier =
                Modifier
                    .wrapContentSize()
                    .background(
                        brush =
                            Brush.horizontalGradient(
                                colors = listOf(Color.Magenta, Color.Cyan),
                            ),
                        shape = RoundedCornerShape(12.dp),
                    )
                    .padding(8.dp),
        )
    }
}

/**
 * A composable function representing a draggable Box.
 * It maintains state variables for offsets and container size, and applies draggable modifiers.
 *
 * @param parentWidth The width of the parent container.
 * @param parentHeight The height of the parent container.
 * @param isDocked Whether the item should dock to the left or right based on its final position after dragging.
 */
@Composable
fun DraggableBox(
    parentWidth: Int,
    parentHeight: Int,
    isDocked: Boolean = true,
) {
    // State variables for offset and visibility
    val offsetX = remember { mutableFloatStateOf(0f) }
    val offsetY = remember { mutableFloatStateOf(0f) }

    // State variables for container size
    val itemContainerHeight = remember { mutableFloatStateOf(0f) }
    val itemContainerWidth = remember { mutableFloatStateOf(0f) }

    // Convert dp to pixels using LocalDensity
    val padding = with(LocalDensity.current) { 0.dp.toPx() }

    // Initialize offsets with padding if padding is greater than 0
    if (padding > 0f) {
        offsetX.floatValue = padding
        offsetY.floatValue = padding
    }

    // Container for the draggable item.
    Box(
        modifier =
            Modifier
                .wrapContentSize()
                .draggableOffset(offsetX, offsetY)
                .draggablePointerInput(
                    offsetX = offsetX,
                    offsetY = offsetY,
                    parentWidth = parentWidth,
                    parentHeight = parentHeight,
                    itemContainerHeight = itemContainerHeight.floatValue,
                    itemContainerWidth = itemContainerWidth.floatValue,
                    padding = padding,
                    isDocked = isDocked,
                )
                .onGloballyPositioned { coordinates ->
                    // Capture the size and position of the composable element
                    // after it has been laid out on the screen
                    itemContainerWidth.floatValue = coordinates.size.width.toFloat()
                    itemContainerHeight.floatValue = coordinates.size.height.toFloat()
                },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Sample Image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(height = 70.dp, width = 60.dp),
            )

            Text(
                text = "Box",
                color = Color.Black,
                fontSize = 9.sp,
                textAlign = TextAlign.Center,
                modifier =
                    Modifier
                        .wrapContentSize()
                        .background(
                            brush = Brush.horizontalGradient(colors = listOf(Color.Magenta, Color.Cyan)),
                            shape = RoundedCornerShape(12.dp),
                        )
                        .padding(8.dp),
            )
        }
    }
}

/**
 * A composable function representing a draggable Image.
 * It maintains state variables for offsets and container size, and applies draggable modifiers.
 *
 * @param parentWidth The width of the parent container.
 * @param parentHeight The height of the parent container.
 * @param isDocked Whether the item should dock to the left or right based on its final position after dragging.
 */
@Composable
fun DraggableImage(
    parentWidth: Int,
    parentHeight: Int,
    isDocked: Boolean = true,
) {
    // State variables for offset and visibility
    val offsetX = remember { mutableFloatStateOf(0f) }
    val offsetY = remember { mutableFloatStateOf(0f) }

    // State variables for container size
    val itemContainerHeight = remember { mutableFloatStateOf(0f) }
    val itemContainerWidth = remember { mutableFloatStateOf(0f) }

    // Convert padding dp to pixels using LocalDensity
    val padding = with(LocalDensity.current) { 5.dp.toPx() }

    // Initialize offsets with padding if padding is greater than 0
    if (padding > 0f) {
        offsetX.floatValue = padding
        offsetY.floatValue = padding
    }

    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Sample Image",
        contentScale = ContentScale.FillHeight,
        modifier =
            Modifier
                .size(height = 100.dp, width = 80.dp)
                .draggableOffset(offsetX, offsetY)
                .draggablePointerInput(
                    offsetX = offsetX,
                    offsetY = offsetY,
                    parentWidth = parentWidth,
                    parentHeight = parentHeight,
                    itemContainerHeight = itemContainerHeight.floatValue,
                    itemContainerWidth = itemContainerWidth.floatValue,
                    padding = padding,
                    isDocked = isDocked,
                )
                .onGloballyPositioned { coordinates ->
                    itemContainerWidth.floatValue = coordinates.size.width.toFloat()
                    itemContainerHeight.floatValue = coordinates.size.height.toFloat()
                },
    )
}
