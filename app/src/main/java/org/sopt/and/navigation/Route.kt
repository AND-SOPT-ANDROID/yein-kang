package org.sopt.and.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route() {
    @Serializable
    data object SignIn : Route()

    @Serializable
    data object SignUp : Route()

    @Serializable
    data object My: Route()

    @Serializable
    data object Home : Route()

    @Serializable
    data object Search : Route()
}