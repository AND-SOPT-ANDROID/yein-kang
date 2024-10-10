package org.sopt.and.signup.Intent

sealed class SignUpIntent {
    data class EnterId(val id: String) : SignUpIntent()
    data class EnterPassword(val password: String) : SignUpIntent()
    data object SignUp : SignUpIntent()
}

