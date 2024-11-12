package org.sopt.and.sign.signup.intent

import androidx.annotation.StringRes

sealed class SignUpSideEffect {
    data class SnackBar(@StringRes val message: Int) : SignUpSideEffect()
    data object SignUp: SignUpSideEffect()
}

