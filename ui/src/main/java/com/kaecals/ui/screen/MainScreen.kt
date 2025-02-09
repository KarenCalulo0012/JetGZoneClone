package com.kaecals.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kaecals.ui.component.HeaderBar
import com.kaecals.ui.component.SideNavDrawer
import com.kaecals.ui.component.SideNavDrawerContent
import com.kaecals.ui.model.navigationItem
import com.kaecals.ui.section.BottomNavigationSection

@Composable
fun MainScreen(content: @Composable (NavHostController) -> Unit = {}) {
    val navController = rememberNavController()
    val isDrawerOpen = remember { mutableStateOf(false) }
    val isAuthScreenVisible = remember { mutableStateOf(false) }
    val isSearchScreenVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                HeaderBar(
                    onSignInClick = { isAuthScreenVisible.value = true },
                    onSearchClick = { isSearchScreenVisible.value = true }
                )
            },
            bottomBar = {
                navController.BottomNavigationSection(
                    items = navigationItem,
                    isDrawerOpen = isDrawerOpen.value,
                    onDrawerToggle = { isDrawerOpen.value = !isDrawerOpen.value },
                    isAuthScreen = isAuthScreenVisible.value,
                    onAuthScreenToggle = { isAuthScreenVisible.value = it },
                    isSearchScreen = isSearchScreenVisible.value,
                    onSearchScreenToggle = {
                        isSearchScreenVisible.value = it
                        isDrawerOpen.value = false
                    }
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                SideNavDrawer(
                    isOpen = isDrawerOpen.value,
                    onClose = { isDrawerOpen.value = false },
                    content = { content(navController) },
                    drawerContent = { SideNavDrawerContent() }
                )
                if(isSearchScreenVisible.value) {
                    SearchScreen(
                        visible = isSearchScreenVisible.value,
                        navController = navController
                    )
                }
            }
        }

        if (isAuthScreenVisible.value) {
            AuthScreen(
                visible = isAuthScreenVisible.value,
                onClose = { isAuthScreenVisible.value = false },
            )
        }
    }
}