package org.sopt.and.presentation.home.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.presentation.component.TypeTabRow
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.home.HomeTabType
import org.sopt.and.presentation.ui.theme.ThirdGrey
import org.sopt.and.presentation.ui.theme.White

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
            color = if (selectedTabIndex == index) White
            else ThirdGrey,
            modifier = modifier
                .wrapContentWidth()
                .padding(horizontal = 8.dp)
                .noRippleClickable(
                    onClick = { onTabClick(index) }
                )
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