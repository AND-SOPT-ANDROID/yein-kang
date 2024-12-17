package org.sopt.and.presentation.my.contract

import org.sopt.and.presentation.util.base.UiEvent

sealed class MyEvent: UiEvent {
    data class GetMyHobby(val token: String): MyEvent()
    data object OnLogoutButtonClick: MyEvent()
}