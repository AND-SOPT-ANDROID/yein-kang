package org.sopt.and.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.extension.getId
import org.sopt.and.extension.getPassword
import org.sopt.and.extension.setIdPassword
import org.sopt.and.home.HomeScreen
import org.sopt.and.main.component.MainBottomBar
import org.sopt.and.my.MyScreen
import org.sopt.and.navigation.Route
import org.sopt.and.search.SearchScreen
import org.sopt.and.sign.signin.SignInScreen
import org.sopt.and.sign.signup.SignUpScreen
import org.sopt.and.ui.theme.FirstGrey
import org.sopt.and.util.PreferenceUtil

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val mainNavigator = remember(navController) { MainNavigator(navController) }

    Scaffold(
        bottomBar = {
            when (mainNavigator.currentTab) {
                MainTabItems.HOME, MainTabItems.SEARCH, MainTabItems.MY -> {
                    MainBottomBar(
                        tabs = MainTabItems.entries,
                        currentTab = mainNavigator.currentTab ?: MainTabItems.HOME,
                        onTabSelected = mainNavigator::navigateTab
                    )
                }
                else -> {}
            }
        }
    ) { innerPadding ->

        val startDestination = getStartDestination()
        MainNavHost(
            navController = navController,
            startDestination = startDestination,
            paddingValues = innerPadding
        )

    }
}

@Composable
private fun getStartDestination(): Route {
    return if(PreferenceUtil.id.isNotBlank() && PreferenceUtil.password.isNotBlank()){
        Route.Home
    } else {
        Route.SignIn
    }
}

@Composable
private fun MainNavHost(
    navController: NavHostController,
    startDestination: Route,
    paddingValues: PaddingValues
) {
    val topBarModifier = Modifier
        .background(FirstGrey)
        .systemBarsPadding()

    val mainModifier = Modifier
        .background(FirstGrey)
        .statusBarsPadding()
        .padding(bottom = paddingValues.calculateBottomPadding())

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        },
    ) {
        composable<Route.SignIn> {
            SignInScreen(
                signUpId = navController.getId(),
                signUpPassword = navController.getPassword(),
                navigateToSignUp = {
                    navController.navigate(Route.SignUp)
                },
                navigateToMy = {
                    navController.navigate(
                        Route.My,
                        navOptions = navOptions {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    )
                },
                onBackButtonClick = {
                    navController.navigateUp()
                },
                onFindInButtonClick = {

                },
                onPasswordResetButtonClick = {

                },
                modifier = topBarModifier
            )
        }

        composable<Route.SignUp> {
            SignUpScreen(
                navigationToSignIn = { id, password ->
                    with(navController) {
                        setIdPassword(id, password)
                        navigateUp()
                    }
                },
                onCloseButtonClick = {
                    navController.navigateUp()
                },
                modifier = topBarModifier
            )
        }

        composable<Route.Home> {
            HomeScreen(
                modifier = mainModifier
            )
        }

        composable<Route.Search> {
            SearchScreen(
                modifier = mainModifier
            )
        }

        composable<Route.My> {
            MyScreen(
                onLogOut = {
                    navController.navigate(
                        Route.SignIn,
                        navOptions = navOptions {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    )
                },
                modifier = mainModifier
            )
        }
    }
}