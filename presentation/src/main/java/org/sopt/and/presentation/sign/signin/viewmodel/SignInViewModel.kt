package org.sopt.and.presentation.sign.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.and.domain.exception.onError
import org.sopt.and.domain.exception.onSuccess
import org.sopt.and.domain.model.UserCredentials
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.R
import org.sopt.and.presentation.delegate.NetworkDelegate
import org.sopt.and.presentation.sign.signin.model.SignInState
import org.sopt.and.presentation.sign.signin.sideeffect.SignInSideEffect
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val networkDelegate: NetworkDelegate
): ViewModel() {

    private var _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private var _intent = MutableSharedFlow<SignInSideEffect>()
    val intent = _intent.asSharedFlow()

    val networkState get() = networkDelegate.networkState

    fun saveUser(id: String, password: String){
        userRepository.saveUser(id, password)
    }

    private fun saveToken(token: String){
        userRepository.saveToken(token)
    }

    fun updateId(id: String) = _state.update {
        it.copy(id = id)
    }

    fun updatePassword(password: String) = _state.update {
        it.copy(password = password)
    }

    fun signIn() = viewModelScope.launch {
        val currentState = _state.value
        val userCredentials = UserCredentials(
            username = currentState.id,
            password = currentState.password
        )
        authRepository.signIn(userCredentials).onSuccess {
            networkDelegate.handleNetworkSuccess()
            saveToken(it.token)
        }.onError {
            networkDelegate.handleSignInError(it)
        }
    }

    fun onSignUpButtonClick() = viewModelScope.launch {
        _intent.emit(SignInSideEffect.SignUp)
    }

    suspend fun handleSignInIntentError(message: String) {
        _intent.emit(SignInSideEffect.SnackBarText(message))
    }

    suspend fun handleSignInIntentSuccess() {
        _intent.emit(SignInSideEffect.SnackBar(R.string.signin_success_text))
        _intent.emit(SignInSideEffect.SignIn)
    }

}