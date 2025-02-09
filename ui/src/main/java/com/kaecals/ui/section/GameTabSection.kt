package com.kaecals.ui.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaecals.jetgzoneclone.ui.R
import com.kaecals.ui.model.GameCategoryModel
import com.kaecals.ui.model.defaultGameCategoryList

@Preview(showBackground = false)
@Composable
fun GameTabSection(
    items: List<GameCategoryModel> = defaultGameCategoryList,
    onSelectChange: (Int) -> Unit = {}
) {
    val selectedTabIndex = remember { mutableIntStateOf(0) }

    TabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex.intValue,
        indicator = { tabPositions ->
            SecondaryIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex.intValue])
                    .padding(horizontal = 24.dp),
            )
        }
    ) {
        items.forEachIndexed { index, item ->
            val selected = selectedTabIndex.intValue == index
            Tab(
                selected = selected,
                onClick = {
                    selectedTabIndex.intValue = index
                    onSelectChange(index)
                },
                text = { Text(text = item.gameCategoryName, fontSize = 10.sp) },
                icon = {
                    Box {
                        if (selected) {
                            Image(
                                painter = painterResource(R.drawable.ic_launcher_background),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Image(
                            painter = painterResource(item.gameCategoryImage),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                },
            )
        }
    }
}
