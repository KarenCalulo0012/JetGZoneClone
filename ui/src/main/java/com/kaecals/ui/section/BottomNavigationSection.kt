package com.kaecals.ui.section

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kaecals.ui.component.rememberGelatinAnimation
import com.kaecals.ui.model.BottomNavItem
import com.kaecals.ui.navigation.MoreRoute
import kotlinx.coroutines.launch

@Composable
fun NavController.BottomNavigationSection(
    items: List<BottomNavItem>,
    isDrawerOpen: Boolean,
    onDrawerToggle: () -> Unit
) {
    val selectedIndex = remember { mutableIntStateOf(if (isDrawerOpen) 0 else 1) }
    val coroutineScope = rememberCoroutineScope()

    NavigationBar {
        items.forEachIndexed { index, item ->
            val color = MaterialTheme.colorScheme
            val selected = index == selectedIndex.intValue
            val iconColor = if (selected) color.primary else color.onSurface
            val textColor = if (selected) color.primary else color.onSurface
            val scaleY = rememberGelatinAnimation(selected)

            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .scale(1f, scaleY.value),
                        imageVector = item.drawable,
                        contentDescription = item.name,
                        tint = iconColor
                    )
                },
                label = { Text(text = item.name, color = textColor) },
                selected = selected,
                onClick = {
                    coroutineScope.launch {
                        if (item.route == MoreRoute) {
                            selectedIndex.intValue = if (isDrawerOpen) 1 else 0
                            onDrawerToggle()
                        } else {
                            selectedIndex.intValue = index
                            this@BottomNavigationSection.navigate(item.route) {
                                this@BottomNavigationSection.graph.startDestinationRoute?.let {
                                    popUpTo(it) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }

                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                interactionSource = remember { MutableInteractionSource() }
            )

        }

    }
}