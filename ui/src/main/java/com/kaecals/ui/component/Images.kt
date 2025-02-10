package com.kaecals.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconImage(
    icon: Int,
    iconSize: Dp = 24.dp,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(icon),
        contentDescription = "Icon Image",
        modifier = modifier.size(iconSize),
        contentScale  = ContentScale.Crop
    )
}