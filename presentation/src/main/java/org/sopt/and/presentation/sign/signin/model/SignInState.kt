package org.sopt.and.presentation.sign.signin.model

import org.sopt.and.presentation.util.KeyUtil.DEFAULT_STRING

data class SignInState(
    val id: String = DEFAULT_STRING,
    val password: String = DEFAULT_STRING,
)
