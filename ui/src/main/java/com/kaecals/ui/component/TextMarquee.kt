package com.kaecals.ui.component

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextMarquee(
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(fontSize = 12.sp, color = Color.Yellow.copy(alpha = 0.4f)),
    text: String,
    // VividViolet was not defined, so using a hex color value
    backgroundColor: Color = Color.DarkGray,
    shape: Shape = RoundedCornerShape(4.dp),
    spacing: Dp = 150.dp,
    // Assuming this is the value you want to use
    repeatDelayMillis: Int = 3000,
    // Assuming this is the value you want to use
    velocity: Dp = 50.dp,
) {
    Row(
        modifier =
        modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = shape)
            .padding(horizontal = 6.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Icon Description",
            modifier = Modifier.size(15.dp),
            tint = Color.Yellow,
        )

        Text(
            modifier =
            Modifier
                .padding(start = 6.dp)
                .basicMarquee(
                    iterations = Int.MAX_VALUE,
                    spacing = MarqueeSpacing(spacing),
                    repeatDelayMillis = repeatDelayMillis,
                    velocity = velocity,
                ),
            style = style,
            text = text,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextMarquee() {
    TextMarquee(text = "Hello this is a test string")
}
