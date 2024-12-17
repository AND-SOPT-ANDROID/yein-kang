package org.sopt.and.presentation.sign.signup.contract

import org.sopt.and.presentation.util.KeyUtil.DEFAULT_STRING
import org.sopt.and.presentation.util.base.UiState

data class SignUpState(
    val id: String = DEFAULT_STRING,
    val password: String = DEFAULT_STRING,
    val hobby: String = DEFAULT_STRING
): UiState
