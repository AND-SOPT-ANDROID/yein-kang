package org.sopt.and.presentation.delegate

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.and.domain.exception.HttpCodeError
import org.sopt.and.domain.exception.NetworkError
import org.sopt.and.domain.exception.UnknownError
import javax.inject.Inject

class NetworkDelegate @Inject constructor() {
    private val _networkState = MutableStateFlow<NetworkState>(NetworkState.Loading)
    val networkState: StateFlow<NetworkState> get() = _networkState.asStateFlow()

    fun handleSignUpError(exception: NetworkError) {
        val updatedException = when(exception){
            is HttpCodeError -> {
                when(exception.title) {
                    "400" -> HttpCodeError(
                        "요청에 문제가 있습니다. ",
                        if (exception.message == "00") "유효하지 못한 요청입니다." else "8자를 넘기는 입력이 있습니다."
                    )
                    "404" -> HttpCodeError(
                        "요청에 문제가 있습니다. ",
                        "유효하지 못한 요청입니다."
                    )
                    "409" -> HttpCodeError(
                        "요청에 문제가 있습니다. ",
                        "이미 존재하는 username입니다."
                    )
                    else -> UnknownError
                }
            }
            else -> exception
        }
        _networkState.value = NetworkState.Error(updatedException.title, updatedException.message)
    }


    fun handleNetworkSuccess(){
        _networkState.value = NetworkState.Success
    }

    fun handleNetworkLoading(){
        _networkState.value = NetworkState.Loading
    }
}

sealed class NetworkState{
    data object Loading: NetworkState()
    data object Success: NetworkState()
    data class Error(val title: String,val msg: String): NetworkState()
}