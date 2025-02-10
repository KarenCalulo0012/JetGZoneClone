package com.kaecals.utils

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration

@OptIn(ExperimentalMaterial3Api::class)
val noRippleTheme =
    RippleConfiguration(
        rippleAlpha = RippleAlpha(0f, 0f, 0f, 0f),
    )