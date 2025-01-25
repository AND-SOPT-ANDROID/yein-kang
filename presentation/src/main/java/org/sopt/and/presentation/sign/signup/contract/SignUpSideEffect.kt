package org.sopt.and.presentation.sign.signup.contract

import androidx.annotation.StringRes
import org.sopt.and.presentation.util.base.UiSideEffect

sealed class SignUpSideEffect: UiSideEffect {
    data class SnackBar(@StringRes val message: Int) : SignUpSideEffect()
    data class SnackBarText(val message: String): SignUpSideEffect()
    data object NavigateToSignIn: SignUpSideEffect()
}

