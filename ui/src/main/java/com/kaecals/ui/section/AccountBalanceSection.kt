package com.kaecals.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kaecals.ui.component.IconImage
import com.tbu.gamezone.ui.R

@Composable
fun AccountBalanceSection(
    modifier: Modifier = Modifier,
    amount: String = "",
    onRefreshClick: () -> Unit = {},
    onDepositClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentSize()
            .padding(vertical = 8.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp))

    ) {
        IconImage(
            icon = R.drawable.ic_coin,
            iconSize = 27.dp,
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable { onDepositClick() }
        )
        Text(amount, modifier = Modifier.padding(8.dp))
        IconImage(
            icon = R.drawable.ic_refresh,
            iconSize = 36.dp,
            modifier = Modifier
                .padding(end = 8.dp)
                .clickable { onRefreshClick() }
        )
    }
}
