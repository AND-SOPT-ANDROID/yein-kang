package org.sopt.and.presentation.sign.signup.viewmodel

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
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.R
import org.sopt.and.presentation.delegate.NetworkDelegate
import org.sopt.and.presentation.sign.signup.intent.SignUpSideEffect
import org.sopt.and.presentation.sign.signup.model.SignUpState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkDelegate: NetworkDelegate
): ViewModel() {

    private var _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    private var _intent = MutableSharedFlow<SignUpSideEffect>()
    val intent = _intent.asSharedFlow()

    val networkState get() = networkDelegate.networkState

    fun updateId(id: String) = _state.update {
        it.copy(id = id)
    }

    fun updatePassword(password: String) = _state.update {
        it.copy(password = password)
    }

    fun updateHobby(hobby: String) = _state.update {
        it.copy(hobby = hobby)
    }

    fun signUp() = viewModelScope.launch {
        val currentState = _state.value
        val user = User(
            username = currentState.id,
            password = currentState.password,
            hobby = currentState.hobby
        )
        userRepository.signUp(user).onSuccess {
            networkDelegate.handleNetworkSuccess()
        }.onError {
            networkDelegate.handleSignUpError(it)
        }
    }

    suspend fun handleSignUpIntentError(message: String) {
        _intent.emit(SignUpSideEffect.SnackBarText(message))
    }

    suspend fun handleSignUpIntentSuccess() {
        _intent.emit(SignUpSideEffect.SnackBar(R.string.signup_success_text))
        _intent.emit(SignUpSideEffect.SignUp)
    }

}

