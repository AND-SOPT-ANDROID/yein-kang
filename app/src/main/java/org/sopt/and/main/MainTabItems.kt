package org.sopt.and.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import org.sopt.and.R
import org.sopt.and.navigation.Route

enum class MainTabItems(
    @StringRes val titleRes: Int,
    val icon: ImageVector,
    val route: Route
){
    HOME(
        titleRes = R.string.main_tab_home,
        icon = Icons.Outlined.Home,
        route = Route.Home
    ),
    SEARCH(
        titleRes = R.string.main_tab_search,
        icon = Icons.Outlined.Search,
        route = Route.Search
    ),
    MY(
        titleRes = R.string.main_tab_my,
        icon = Icons.Outlined.Person,
        route = Route.My
    )
}
