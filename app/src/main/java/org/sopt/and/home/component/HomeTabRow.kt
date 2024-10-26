package org.sopt.and.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.component.TypeTabRow
import org.sopt.and.home.HomeTabType
import org.sopt.and.ui.theme.ThirdGrey

@Composable
fun HomeTabRow(
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TypeTabRow(
        tabTitles = HomeTabType.entries.map { it.titleRes }.toList(),
        selectedTabIndex = selectedTabIndex,
        modifier = modifier
    ) { index, tab ->
        Text(
            text = stringResource(tab),
            color = if (selectedTabIndex == index) Color.White
            else ThirdGrey,
            modifier = modifier
                .wrapContentWidth()
                .padding(horizontal = 8.dp)
                .clickable { onTabClick(index) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeTabRowPreview() {
    HomeTabRow(
        selectedTabIndex = 0,
        onTabClick = {},
        modifier = Modifier.wrapContentSize()
    )
}