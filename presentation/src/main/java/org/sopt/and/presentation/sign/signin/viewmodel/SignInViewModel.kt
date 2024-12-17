package org.sopt.and.presentation.sign.signin.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.and.domain.exception.onError
import org.sopt.and.domain.exception.onSuccess
import org.sopt.and.domain.model.UserCredentials
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.R
import org.sopt.and.presentation.delegate.NetworkDelegate
import org.sopt.and.presentation.sign.signin.contract.SignInEvent
import org.sopt.and.presentation.sign.signin.contract.SignInState
import org.sopt.and.presentation.sign.signin.contract.SignInSideEffect
import org.sopt.and.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val networkDelegate: NetworkDelegate
): BaseViewModel<SignInState, SignInEvent, SignInSideEffect>() {

    override fun createInitialState(): SignInState = SignInState()

    override suspend fun handleEvent(event: SignInEvent) {
        when(event) {
            is SignInEvent.OnIdChanged -> {
                setState { copy(id = event.id) }
            }
            is SignInEvent.OnPasswordChanged -> {
                setState { copy(password = event.password) }
            }
            SignInEvent.OnSignInButtonClick -> {
                signIn()
            }
            SignInEvent.OnSignUpButtonClick -> {
                navigateToSignUp()
            }
        }
    }

    val networkState get() = networkDelegate.networkState

    private fun saveToken(token: String){
        userRepository.saveToken(token)
    }

    fun signIn() = viewModelScope.launch {
        val currentState = uiState.value
        val userCredentials = UserCredentials(
            username = currentState.id,
            password = currentState.password
        )
        authRepository.signIn(userCredentials).onSuccess {
            networkDelegate.handleNetworkSuccess()
            saveToken(it.token)
            navigateToMy()
        }.onError {
            networkDelegate.handleSignInError(it)
        }
    }

    private fun navigateToSignUp() = viewModelScope.launch {
        setSideEffect(SignInSideEffect.NavigateToSignUp)
    }

    private fun navigateToMy() = viewModelScope.launch {
        delay(100)
        setSideEffect(SignInSideEffect.NavigateToMy)
    }

    fun handleSignInSideEffectError(message: String) = viewModelScope.launch {
        setSideEffect(SignInSideEffect.SnackBarText(message))
    }

    fun handleSignInSideEffectSuccess() = viewModelScope.launch {
        setSideEffect(SignInSideEffect.SnackBar(R.string.signin_success_text))
    }

}