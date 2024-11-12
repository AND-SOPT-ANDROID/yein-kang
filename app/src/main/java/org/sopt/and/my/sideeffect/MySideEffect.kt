package org.sopt.and.my.sideeffect

import androidx.annotation.StringRes

sealed class MySideEffect {
    data class SnackBar(@StringRes val message: Int): MySideEffect()
    data object LogOut: MySideEffect()
}