package org.sopt.and.presentation.sign.signup.intent

import androidx.annotation.StringRes

sealed class SignUpSideEffect {
    data class SnackBar(@StringRes val message: Int) : SignUpSideEffect()
    data class SnackBarText(val message: String): SignUpSideEffect()
    data object SignUp: SignUpSideEffect()
}

