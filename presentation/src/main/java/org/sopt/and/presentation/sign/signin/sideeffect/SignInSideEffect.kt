package org.sopt.and.presentation.sign.signin.sideeffect

import androidx.annotation.StringRes

sealed class SignInSideEffect {
    data class SnackBar(@StringRes val message: Int) : SignInSideEffect()
    data class SnackBarText(val message: String) : SignInSideEffect()
    data object SignIn : SignInSideEffect()
    data object SignUp: SignInSideEffect()
}