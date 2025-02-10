package com.kaecals.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaecals.ui.section.AccountBalanceSection
import com.tbu.gamezone.ui.R

@Preview
@Composable
fun HeaderBar(
    isSignedIn: Boolean = true,
    onSignInClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp)
            .statusBarsPadding()
            .background(color = Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isSignedIn) {
            IconImage(R.drawable.ic_logo, 36.dp)
            IconRoundedCornerBackground(
                icon = Icons.Default.KeyboardArrowDown,
                modifier = Modifier.padding(start = 12.dp, end = 8.dp),
                onIconClick = {}
            )
            AccountBalanceSection(amount = "983,187.15")
            Spacer(modifier = Modifier.weight(1f))
            IconRoundedCornerBackground(
                icon = Icons.Default.Notifications,
                hasNotification = true,
                onIconClick = {}
            )
        } else {
            Text(
                text = "Sign In",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.clickable {
                    onSignInClick()
                })
            ImageButton(
                onClick = onSignInClick,
                painter = painterResource(R.drawable.bg_rounded_parallelogram),
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            ) {
                HighlightedText(
                    text = "Register for ₱ 8888 bonus",
                    textHighlight = "₱ 8888"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        IconRoundedCornerBackground(
            icon = Icons.Default.Search,
            onIconClick = onSearchClick,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}