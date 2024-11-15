package org.sopt.and.presentation.my.viewmodel

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
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.delegate.NetworkDelegate
import org.sopt.and.presentation.my.model.MyState
import org.sopt.and.presentation.my.sideeffect.MySideEffect
import org.sopt.and.presentation.sign.signin.sideeffect.SignInSideEffect
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkDelegate: NetworkDelegate
): ViewModel() {

    private val _state = MutableStateFlow(MyState())
    val state = _state.asStateFlow()

    private val _intent = MutableSharedFlow<MySideEffect>()
    val intent = _intent.asSharedFlow()

    val networkState get() = networkDelegate.networkState

    init {
      getMyHobby()
    }

    fun getMyHobby() = viewModelScope.launch {
        val token = userRepository.getToken()
        userRepository.getMyHobby(token).onSuccess { result ->
            _state.update {
                it.copy(hobby = result.hobby)
            }
            networkDelegate.handleNetworkSuccess()
        }.onError {
            networkDelegate.handleGetMyHobbyError(it)
        }
    }

    fun onLogOutButtonClick() = viewModelScope.launch {
        _intent.emit(MySideEffect.Logout)
    }

    fun clearUserPreference() = viewModelScope.launch {
        userRepository.clearUserPreference()
    }

    suspend fun handleMyIntentError(message: String) {
        _intent.emit(MySideEffect.SnackBarText(message))
    }

}