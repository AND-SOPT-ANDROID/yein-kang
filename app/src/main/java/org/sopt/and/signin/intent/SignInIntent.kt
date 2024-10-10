package org.sopt.and.signin.intent

sealed class SignInIntent {
    data class EnterId(val id: String) : SignInIntent()
    data class EnterPassword(val password: String) : SignInIntent()
    data object SignIn : SignInIntent()

}