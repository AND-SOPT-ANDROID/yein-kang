package org.sopt.and.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import org.sopt.and.presentation.navigation.Route

class MainNavigator(
    private val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTab: MainTabItems?
        @Composable get() {
            val destination = currentDestination
            return MainTabItems.entries.find { tab ->
                destination?.route == tab.route::class.qualifiedName
            }
        }

    fun navigateTab(tab: MainTabItems){

        val mainNavOptions = navOptions {
            popUpTo(navController.graph.startDestinationId){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTabItems.HOME -> navController.navigate(Route.Home, mainNavOptions)
            MainTabItems.SEARCH -> navController.navigate(Route.Search, mainNavOptions)
            MainTabItems.MY -> navController.navigate(Route.My, mainNavOptions)
        }
    }
}