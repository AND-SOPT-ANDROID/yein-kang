package org.sopt.and.presentation.sign.signup.model

import org.sopt.and.presentation.util.KeyUtil.DEFAULT_STRING

data class SignUpState(
    val id: String = DEFAULT_STRING,
    val password: String = DEFAULT_STRING,
    val hobby: String = DEFAULT_STRING
)
