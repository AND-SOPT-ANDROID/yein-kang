package org.sopt.and.presentation.sign.signin.contract

import androidx.annotation.StringRes
import org.sopt.and.presentation.util.base.UiSideEffect

sealed class SignInSideEffect: UiSideEffect {
    data class SnackBar(@StringRes val message: Int) : SignInSideEffect()
    data class SnackBarText(val message: String) : SignInSideEffect()
    data object NavigateToSignUp: SignInSideEffect()
    data object NavigateToMy: SignInSideEffect()
}