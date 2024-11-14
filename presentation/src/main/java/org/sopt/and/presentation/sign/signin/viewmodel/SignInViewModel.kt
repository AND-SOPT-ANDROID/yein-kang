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
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.R
import org.sopt.and.presentation.sign.signin.model.SignInState
import org.sopt.and.presentation.sign.signin.sideeffect.SignInSideEffect
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private var _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private var _intent = MutableSharedFlow<SignInSideEffect>()
    val intent = _intent.asSharedFlow()

    fun saveUser(id: String, password: String){
        userRepository.saveUser(id, password)
    }

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