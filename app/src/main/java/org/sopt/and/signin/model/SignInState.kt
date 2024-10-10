package org.sopt.and.signin.model

data class SignInState(
    val id: String = "",
    val password: String = "",
    val signInSuccessful: Boolean = false,
    val snackBarMessage: String? = null
)
