package com.kaecals.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kaecals.ui.component.enterSlideInVertically
import com.kaecals.ui.component.exitSlideOutVertically

@Composable
fun SearchScreen(visible: Boolean, navController: NavController = rememberNavController()) {
    BackHandler { navController.popBackStack() }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {
        AnimatedVisibility(
            visible = visible,
            enter = enterSlideInVertically(),
            exit = exitSlideOutVertically()
        ) {
            Text("Search Screen")
        }
    }
}
