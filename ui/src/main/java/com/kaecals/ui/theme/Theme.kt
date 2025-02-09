package com.kaecals.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryLightColor,
    secondary = SecondaryColor,
    secondaryContainer = PrimaryDarkColor,
    background = Color.White, // Set background to White for light mode
    surface = SurfaceColor,
    error = ErrorColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor,
    onBackground = Color.Black, // Set text color to Black for light mode
    onSurface = OnSurfaceColor,
    onError = OnErrorColor
)

private val DarkColors = darkColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryDarkColor,
    secondary = SecondaryColor,
    secondaryContainer = PrimaryLightColor,
    background = Color.Black, // Set background to Black for dark mode
    surface = Color.DarkGray,
    error = ErrorColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor,
    onBackground = Color.White, // Set text color to White for dark mode
    onSurface = OnSurfaceColor,
    onError = OnErrorColor
)

@Composable
fun JetGZoneCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = CustomTypography,
        content = content
    )
}