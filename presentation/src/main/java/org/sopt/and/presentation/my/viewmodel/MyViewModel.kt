package org.sopt.and.presentation.my.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.and.domain.exception.onError
import org.sopt.and.domain.exception.onSuccess
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.delegate.NetworkDelegate
import org.sopt.and.presentation.my.contract.MyEvent
import org.sopt.and.presentation.my.model.MyState
import org.sopt.and.presentation.my.sideeffect.MySideEffect
import org.sopt.and.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkDelegate: NetworkDelegate
): BaseViewModel<MyState, MyEvent, MySideEffect>() {

    override fun createInitialState(): MyState = MyState()

    override suspend fun handleEvent(event: MyEvent) {
        when(event) {
            MyEvent.OnLogoutButtonClick -> {
                navigateToSignIn()
            }
            is MyEvent.GetMyHobby -> {
                setState { copy(hobby = event.hobby) }
            }
        }
    }

    val networkState get() = networkDelegate.networkState

    fun getMyHobby() = viewModelScope.launch {
        val token = userRepository.getToken()
        userRepository.getMyHobby(token).onSuccess { result ->
            setEvent(MyEvent.GetMyHobby(result.hobby))
            networkDelegate.handleNetworkSuccess()
        }.onError {
            networkDelegate.handleGetMyHobbyError(it)
        }
    }

    private fun navigateToSignIn() = viewModelScope.launch {
        delay(100)
        setSideEffect(MySideEffect.NavigateToSignIn)
    }

    fun clearUserPreference() = viewModelScope.launch {
        userRepository.clearUserPreference()
    }

    fun handleMyIntentError(message: String) {
        setSideEffect(MySideEffect.SnackBarText(message))
    }

}