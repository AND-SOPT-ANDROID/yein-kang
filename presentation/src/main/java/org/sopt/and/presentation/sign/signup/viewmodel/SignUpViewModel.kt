package org.sopt.and.presentation.sign.signup.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.and.domain.exception.onError
import org.sopt.and.domain.exception.onSuccess
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.R
import org.sopt.and.presentation.delegate.NetworkDelegate
import org.sopt.and.presentation.sign.signup.contract.SignUpEvent
import org.sopt.and.presentation.sign.signup.contract.SignUpSideEffect
import org.sopt.and.presentation.sign.signup.contract.SignUpState
import org.sopt.and.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkDelegate: NetworkDelegate
): BaseViewModel<SignUpState, SignUpEvent, SignUpSideEffect>() {

    val networkState get() = networkDelegate.networkState

    override fun createInitialState(): SignUpState = SignUpState()

    override suspend fun handleEvent(event: SignUpEvent) {
        when(event) {
            is SignUpEvent.OnIdChanged -> {
                setState { copy(id = event.id) }
            }
            is SignUpEvent.OnPasswordChanged -> {
                setState { copy(password = event.password) }
            }
            is SignUpEvent.OnHobbyChanged -> {
                setState { copy(hobby = event.hobby) }
            }
            SignUpEvent.OnSignUpButtonClick -> {
                signUp()
            }
        }
    }

    fun signUp() = viewModelScope.launch {
        val currentState = uiState.value
        val user = User(
            username = currentState.id,
            password = currentState.password,
            hobby = currentState.hobby
        )
        userRepository.signUp(user).onSuccess {
            networkDelegate.handleNetworkSuccess()
            navigateToSignIn()
        }.onError {
            networkDelegate.handleSignUpError(it)
        }
    }

    private fun navigateToSignIn() = viewModelScope.launch {
        delay(100)
        setSideEffect(SignUpSideEffect.NavigateToSignIn)
    }

    fun handleSignUpIntentError(message: String) {
        setSideEffect(SignUpSideEffect.SnackBarText(message))
    }

    fun handleSignUpIntentSuccess() {
        setSideEffect(SignUpSideEffect.SnackBar(R.string.signup_success_text))
    }

}

