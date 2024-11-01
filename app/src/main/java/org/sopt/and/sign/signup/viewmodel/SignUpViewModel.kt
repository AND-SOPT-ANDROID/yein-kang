package org.sopt.and.sign.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.sign.signup.intent.SignUpSideEffect
import org.sopt.and.sign.signup.model.SignUpState

class SignUpViewModel: ViewModel() {

    private var _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    private var _intent = MutableSharedFlow<SignUpSideEffect>()
    val intent = _intent.asSharedFlow()

    fun updateId(id: String) = _state.update {
        it.copy(id = id)
    }

    fun updatePassword(password: String) = _state.update {
        it.copy(password = password)
    }

    fun onSignUpButtonClick() = viewModelScope.launch {
        val isValidateId = state.value.id.matches(emailPattern)
        val isValidatePassword = state.value.password.matches(passwordPattern)
        when{
            isValidateId && isValidatePassword -> {
                _intent.emit(SignUpSideEffect.SnackBar(R.string.signup_success_text))
                _intent.emit(SignUpSideEffect.SignUp)
            }
            !isValidateId -> {
                _intent.emit(SignUpSideEffect.SnackBar(R.string.signup_failure_email_text))
            }
            !isValidatePassword -> {
                _intent.emit(SignUpSideEffect.SnackBar(R.string.signup_failure_password_text))
            }
            else -> {
                _intent.emit(SignUpSideEffect.SnackBar(R.string.signup_failure_text))
            }
        }
    }

    companion object {
        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        val passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}$".toRegex()
    }
}

