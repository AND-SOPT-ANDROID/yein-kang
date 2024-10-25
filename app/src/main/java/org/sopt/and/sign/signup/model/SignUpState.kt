package org.sopt.and.sign.signup.model

import org.sopt.and.util.KeyUtil.DEFAULT_STRING

data class SignUpState(
    val id: String = DEFAULT_STRING,
    val password: String = DEFAULT_STRING
)
