package org.sopt.and.presentation.my.sideeffect

import androidx.annotation.StringRes

sealed class MySideEffect {
    data class SnackBar(@StringRes val message: Int): MySideEffect()
    data class SnackBarText(val message: String): MySideEffect()
    data object Logout: MySideEffect()
}