package com.kaecals.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kaecals.ui.model.navigationItem
import com.kaecals.ui.section.BottomNavigationSection

@Composable
fun MainScreen(content: @Composable (NavHostController) -> Unit = {}) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {},
        bottomBar = { navController.BottomNavigationSection(navigationItem) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            content(navController)
        }
    }
}