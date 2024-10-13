package org.sopt.and.signup.model

data class SignUpState(
    val id: String = "",
    val password: String = "",
    val isSignUpSuccess: Boolean = false,
    val snackBarMessage: String? = null
)
