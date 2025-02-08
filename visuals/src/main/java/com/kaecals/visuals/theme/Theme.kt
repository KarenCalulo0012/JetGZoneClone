package com.kaecals.jetgzoneclone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.kaecals.visuals.theme.BackgroundColor
import com.kaecals.visuals.theme.CustomTypography
import com.kaecals.visuals.theme.ErrorColor
import com.kaecals.visuals.theme.OnBackgroundColor
import com.kaecals.visuals.theme.OnErrorColor
import com.kaecals.visuals.theme.OnPrimaryColor
import com.kaecals.visuals.theme.OnSecondaryColor
import com.kaecals.visuals.theme.OnSurfaceColor
import com.kaecals.visuals.theme.PrimaryColor
import com.kaecals.visuals.theme.PrimaryDarkColor
import com.kaecals.visuals.theme.PrimaryLightColor
import com.kaecals.visuals.theme.SecondaryColor
import com.kaecals.visuals.theme.SurfaceColor

private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryLightColor,
    secondary = SecondaryColor,
    secondaryContainer = PrimaryDarkColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    error = ErrorColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor,
    onBackground = OnBackgroundColor,
    onSurface = OnSurfaceColor,
    onError = OnErrorColor
)

private val DarkColors = darkColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryDarkColor,
    secondary = SecondaryColor,
    secondaryContainer = PrimaryLightColor,
    background = Color.Black,
    surface = Color.DarkGray,
    error = ErrorColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor,
    onBackground = OnBackgroundColor,
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