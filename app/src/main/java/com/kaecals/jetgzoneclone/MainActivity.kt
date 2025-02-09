package com.kaecals.jetgzoneclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kaecals.jetgzoneclone.navigation.MainNavHost
import com.kaecals.ui.screen.MainScreen
import com.kaecals.ui.theme.JetGZoneCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetGZoneCloneTheme {
                MainScreen(content = { MainNavHost(it) })
            }
        }
    }
}