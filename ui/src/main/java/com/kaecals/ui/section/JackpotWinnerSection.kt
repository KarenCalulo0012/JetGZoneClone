package com.kaecals.ui.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaecals.jetgzoneclone.ui.R
import com.kaecals.ui.component.AutoScrollList
import com.kaecals.ui.model.jackpotWinnerList
import com.kaecals.utils.maskName
import java.text.DecimalFormat

@Composable
fun JackpotWinnerSection() {
    val color = MaterialTheme.colorScheme
    AutoScrollList(jackpotWinnerList, batchCount = 1) { jackpotWinner ->
        val decimalFormat = DecimalFormat("#,###")
        val jackpotPrice = decimalFormat.format(jackpotWinner.jackpotAmount)
        Row(
            modifier =
            Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp,
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = jackpotWinner.avatar),
                contentDescription = "Icon Description",
                modifier = Modifier.size(28.dp),
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = maskName(jackpotWinner.userName),
                style =
                TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = color.primary,
                ),
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = jackpotWinner.time,
                style =
                TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = color.primary,
                ),
            )

            Spacer(Modifier.width(10.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Icon Description",
                modifier = Modifier.size(20.dp),
            )

            Spacer(Modifier.width(2.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = jackpotPrice,
                color = Color(0xFF801ED2),
                style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 16.sp),
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Icon Description",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified,
            )
        }
    }

}