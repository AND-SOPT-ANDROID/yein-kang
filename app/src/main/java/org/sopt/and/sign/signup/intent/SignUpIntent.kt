package org.sopt.and.sign.signup.intent

import androidx.annotation.StringRes

sealed class SignUpIntent {
    data class SnackBar(@StringRes val message: Int) : SignUpIntent()
    data object SignUp: SignUpIntent()
}

