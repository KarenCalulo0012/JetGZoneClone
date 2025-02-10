package com.kaecals.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HighlightedText(modifier: Modifier = Modifier, text: String, textHighlight: String) {
    val annotatedText = buildAnnotatedString {
        val bonusStartIndex = text.indexOf(textHighlight)
        val bonusEndIndex = bonusStartIndex + textHighlight.length

        append(text.substring(0, bonusStartIndex))
        withStyle(style = SpanStyle(color = Color.Yellow, fontSize = 16.sp)) {
            append(textHighlight)
        }
        append(text.substring(bonusEndIndex))
    }

    Text(
        text = annotatedText,
        modifier = modifier.padding(16.dp),
        style = TextStyle(fontSize = 16.sp, color = Color.White)
    )
}