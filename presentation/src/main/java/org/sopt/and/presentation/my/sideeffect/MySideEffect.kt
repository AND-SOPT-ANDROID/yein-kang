package org.sopt.and.presentation.my.sideeffect

import androidx.annotation.StringRes
import org.sopt.and.presentation.util.base.UiSideEffect

sealed class MySideEffect: UiSideEffect {
    data class SnackBar(@StringRes val message: Int): MySideEffect()
    data class SnackBarText(val message: String): MySideEffect()
    data object NavigateToSignIn: MySideEffect()
}