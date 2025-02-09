package com.kaecals.ui.component.floatingContent

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

/**
 * A composable layout that provides a container for draggable items.
 * Measures the dimensions of its parent and passes those dimensions to its children.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param draggableContents A list of composable functions representing the draggable items. Each item receives the parent width and height as parameters.
 */
@Composable
fun DraggableLayout(
    modifier: Modifier = Modifier,
    draggableContents: List<@Composable (Int, Int) -> Unit>,
) {
    var parentWidth by remember { mutableIntStateOf(0) }
    var parentHeight by remember { mutableIntStateOf(0) }

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates ->
                    parentWidth = coordinates.size.width
                    parentHeight = coordinates.size.height
                },
    ) {
        draggableContents.forEach { draggableContent ->
            draggableContent(parentWidth, parentHeight)
        }
    }
}

/**
 * A composable layout that provides a container for a single draggable item.
 * Measures the dimensions of its parent and passes those dimensions to the child composable.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param draggableContent A composable function representing the draggable item. It receives the parent width and height as parameters.
 */
@Composable
fun DraggableLayout(
    modifier: Modifier = Modifier,
    draggableContent: @Composable (Int, Int) -> Unit,
) {
    var parentWidth by remember { mutableIntStateOf(0) }
    var parentHeight by remember { mutableIntStateOf(0) }

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates ->
                    parentWidth = coordinates.size.width
                    parentHeight = coordinates.size.height
                },
    ) {
        draggableContent(parentWidth, parentHeight)
    }
}

/**
 * Adds draggable functionality to a composable element, allowing it to be moved within specified bounds.
 *
 * @param offsetX The mutable state representing the X offset of the element.
 * @param offsetY The mutable state representing the Y offset of the element.
 * @param parentWidth The width of the parent container.
 * @param parentHeight The height of the parent container.
 * @param itemContainerWidth The width of the draggable item.
 * @param itemContainerHeight The height of the draggable item.
 * @param padding The padding to be applied around the element.
 * @param isDocked Whether the element should dock to the left or right based on its final position after dragging.
 * @return A Modifier with draggable functionality.
 *
 *
 * The `dockingThreshold` represents 40% of the parent container's width.
 * This value determines whether the draggable item should dock to the left
 * or right side of the parent container when the user stops dragging it.
 * If the draggable item's horizontal position is less than the threshold,
 * it docks to the left edge; otherwise, it docks to the right edge.
 */

fun Modifier.draggablePointerInput(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    parentWidth: Int,
    parentHeight: Int,
    itemContainerHeight: Float,
    itemContainerWidth: Float,
    padding: Float = 0f,
    isDocked: Boolean = true,
): Modifier =
    this.pointerInput(Unit) {
        detectDragGestures(
            onDragEnd = {
                if (isDocked) {
                    // Calculate dynamic docking threshold based on parent width
                    val dockingThreshold = parentWidth * 0.4f

                    // Docking logic after dragging ends
                    offsetX.value =
                        if (offsetX.value < dockingThreshold) {
                            padding // Dock to the left
                        } else {
                            // Dock to the right
                            parentWidth - itemContainerWidth - padding
                        }
                }
            },
        ) { change, dragAmount ->
            change.consume()
            // Update offset and restrict within parent bounds
            val newOffsetX = (offsetX.value + dragAmount.x).coerceIn(padding, parentWidth - itemContainerWidth - padding)
            val newOffsetY = (offsetY.value + dragAmount.y).coerceIn(padding, parentHeight - itemContainerHeight - padding)
            offsetX.value = newOffsetX
            offsetY.value = newOffsetY
        }
    }

/**
 * Offsets a composable element based on provided X and Y offsets.
 *
 * @param offsetX The mutable state representing the X offset of the element.
 * @param offsetY The mutable state representing the Y offset of the element.
 * @return A Modifier with offset functionality applied.
 */
fun Modifier.draggableOffset(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
): Modifier =
    this.offset {
        IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt())
    }
