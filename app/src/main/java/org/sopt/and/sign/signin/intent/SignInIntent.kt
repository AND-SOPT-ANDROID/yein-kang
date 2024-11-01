package org.sopt.and.sign.signin.intent

import androidx.annotation.StringRes

sealed class SignInIntent {
    data class SnackBar(@StringRes val message: Int) : SignInIntent()
    data object SignIn : SignInIntent()
    data object SignUp: SignInIntent()
}