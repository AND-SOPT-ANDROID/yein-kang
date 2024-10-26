package org.sopt.and.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.FirstGrey

@Composable
fun TypeTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabTitles: List<Int> = emptyList(),
    containerColor: Color = FirstGrey,
    indicator: @Composable (List<TabPosition>) -> Unit = {},
    content: @Composable RowScope.(Int, Int) -> Unit = { _, _ -> }
){
    val scrollState = rememberScrollState()

    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = containerColor,
        indicator = indicator
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp)
        ) {
            tabTitles.forEachIndexed { index, title ->
                content(index, title)
            }
        }

    }
}