package org.sopt.and.presentation.sign.signin.contract

import org.sopt.and.presentation.util.KeyUtil.DEFAULT_STRING
import org.sopt.and.presentation.util.base.UiState

data class SignInState(
    val id: String = DEFAULT_STRING,
    val password: String = DEFAULT_STRING,
): UiState
