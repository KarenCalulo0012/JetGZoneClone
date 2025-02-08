package com.kaecals.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaecals.jetgzoneclone.ui.R

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Row(modifier = Modifier.fillMaxWidth().padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.clip(CircleShape).size(50.dp)) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null
            )
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
        }
        Text(
            text = "Hello $name!",
            modifier = modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("Android")
}