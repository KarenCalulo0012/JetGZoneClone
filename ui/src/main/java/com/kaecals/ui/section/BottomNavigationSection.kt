package com.kaecals.ui.section

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kaecals.ui.component.rememberGelatinAnimation
import com.kaecals.ui.model.BottomNavItem
import com.kaecals.ui.navigation.AccountRoute
import com.kaecals.ui.navigation.HomeRoute
import com.kaecals.ui.navigation.MoreRoute
import com.kaecals.ui.navigation.Route
import com.kaecals.utils.noRippleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavController.BottomNavigationSection(
    items: List<BottomNavItem>,
    isDrawerOpen: Boolean,
    onDrawerToggle: () -> Unit,
    isAuthScreen: Boolean,
    onAuthScreenToggle: (Boolean) -> Unit,
    isSearchScreen: Boolean,
    onSearchScreenToggle: (Boolean) -> Unit,
) {
    val selectedIndex = remember { mutableIntStateOf(if (isDrawerOpen) 0 else 1) }
    val navigateState = remember { mutableStateOf<Route?>(null) }

    LaunchedEffect(navigateState.value) {
        if(navigateState.value != null) {
            this@BottomNavigationSection.navigate(navigateState.value!!) {
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

    NavigationBar {
        items.forEachIndexed { index, item ->
            val color = MaterialTheme.colorScheme
            val selected = index == selectedIndex.intValue
            val iconColor = if (selected) color.primary else color.onSurface
            val textColor = if (selected) color.primary else color.onSurface
            val scaleY = rememberGelatinAnimation(selected)

            CompositionLocalProvider(LocalRippleConfiguration provides noRippleTheme) {
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
                        when (item.route) {
                            MoreRoute -> {
                                selectedIndex.intValue = if (isDrawerOpen) 1 else 0
                                if (selectedIndex.intValue == 0) navigateState.value = HomeRoute
                                onDrawerToggle()
                            }
                            AccountRoute -> {
                                selectedIndex.intValue = if (isAuthScreen.not()) selectedIndex.intValue else index
                                onAuthScreenToggle(true)
                                navigateState.value = items[selectedIndex.intValue].route
                            }
                            else -> {
                                selectedIndex.intValue = if(isSearchScreen) 1 else index
                                navigateState.value = if(isSearchScreen) HomeRoute else item.route
                                onSearchScreenToggle(false)
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                    interactionSource = remember { MutableInteractionSource() }
                )
            }
        }
    }
}