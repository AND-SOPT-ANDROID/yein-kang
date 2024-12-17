package org.sopt.and.presentation.sign.signin.contract

import org.sopt.and.presentation.util.base.UiEvent

sealed class SignInEvent: UiEvent {
    data class OnIdChanged(val id: String) : SignInEvent()
    data class OnPasswordChanged(val password: String) : SignInEvent()
    data object OnSignInButtonClick : SignInEvent()
    data object OnSignUpButtonClick : SignInEvent()
}