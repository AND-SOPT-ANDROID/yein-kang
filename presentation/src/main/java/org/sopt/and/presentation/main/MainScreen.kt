package org.sopt.and.presentation.main

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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.presentation.extension.getId
import org.sopt.and.presentation.extension.getPassword
import org.sopt.and.presentation.extension.setIdPassword
import org.sopt.and.presentation.home.HomeScreen
import org.sopt.and.presentation.main.component.MainBottomBar
import org.sopt.and.presentation.main.viewmodel.MainViewModel
import org.sopt.and.presentation.my.MyScreen
import org.sopt.and.presentation.navigation.Route
import org.sopt.and.presentation.search.SearchScreen
import org.sopt.and.presentation.sign.signin.SignInScreen
import org.sopt.and.presentation.sign.signup.SignUpScreen
import org.sopt.and.presentation.ui.theme.FirstGrey

@Composable
fun MainScreen(
    viewmodel: MainViewModel = hiltViewModel()
) {

    val navController = rememberNavController()
    val mainNavigator = remember(navController) { MainNavigator(navController) }
    val startDestination = viewmodel.getStartDestination()

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

        MainNavHost(
            navController = navController,
            startDestination = startDestination,
            paddingValues = innerPadding
        )

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