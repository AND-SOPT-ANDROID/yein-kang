package org.sopt.and.presentation.sign.signup.contract

import org.sopt.and.presentation.util.base.UiEvent

sealed class SignUpEvent: UiEvent {
    data class OnIdChanged(val id: String) : SignUpEvent()
    data class OnPasswordChanged(val password: String) : SignUpEvent()
    data class OnHobbyChanged(val hobby: String) : SignUpEvent()
    data object OnSignUpButtonClick : SignUpEvent()
}