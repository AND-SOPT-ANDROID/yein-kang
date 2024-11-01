package org.sopt.and.my.intent

import androidx.annotation.StringRes

sealed class MyIntent {
    data class SnackBar(@StringRes val message: Int): MyIntent()
    data object LogOut: MyIntent()
}