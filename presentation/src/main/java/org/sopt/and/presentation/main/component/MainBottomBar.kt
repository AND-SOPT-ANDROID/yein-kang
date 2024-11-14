package org.sopt.and.presentation.main.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.presentation.main.MainTabItems
import org.sopt.and.presentation.ui.theme.FirstGrey
import org.sopt.and.presentation.ui.theme.ThirdGrey

@Composable
fun MainBottomBar(
    tabs: List<MainTabItems>,
    currentTab: MainTabItems,
    onTabSelected: (MainTabItems) -> Unit
) {
    NavigationBar(
        modifier = Modifier.height(80.dp),
        containerColor = FirstGrey
    ){
        tabs.forEach { tabItem ->
            NavigationBarItem(
                selected = currentTab == tabItem,
                onClick = { onTabSelected(tabItem) },
                icon = {
                    Icon(
                        imageVector = tabItem.icon,
                        contentDescription = stringResource(id = tabItem.titleRes),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        stringResource(id = tabItem.titleRes),
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                colors = NavigationBarItemDefaults
                    .colors(
                        selectedIconColor = White,
                        selectedTextColor = White,
                        unselectedIconColor = ThirdGrey,
                        unselectedTextColor = ThirdGrey,
                        indicatorColor = FirstGrey
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainBottomBarPreview() {
    MainBottomBar(
        tabs = MainTabItems.entries,
        currentTab = MainTabItems.HOME,
        onTabSelected = {}
    )
}