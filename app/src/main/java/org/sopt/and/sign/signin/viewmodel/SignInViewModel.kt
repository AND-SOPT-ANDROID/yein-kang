package org.sopt.and.sign.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.sign.signin.sideeffect.SignInSideEffect
import org.sopt.and.sign.signin.model.SignInState

class SignInViewModel: ViewModel() {

    private var _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private var _intent = MutableSharedFlow<SignInSideEffect>()
    val intent = _intent.asSharedFlow()

    fun updateId(id: String) = _state.update {
        it.copy(id = id)
    }

    fun updatePassword(password: String) = _state.update {
        it.copy(password = password)
    }

    fun onSignUpButtonClick() = viewModelScope.launch {
        _intent.emit(SignInSideEffect.SignUp)
    }

    fun onSignInButtonClick(id: String, password: String) = viewModelScope.launch {
        if(isValidateSignIn(id, password)) {
            _intent.emit(SignInSideEffect.SnackBar(R.string.signin_success_text))
            _intent.emit(SignInSideEffect.SignIn)
        } else {
            _intent.emit(SignInSideEffect.SnackBar(R.string.signin_failure_text))
        }
    }
    private fun isValidateSignIn(id: String, password: String): Boolean {
        return state.value.let {
            it.id == id && it.password == password
        }
    }

}