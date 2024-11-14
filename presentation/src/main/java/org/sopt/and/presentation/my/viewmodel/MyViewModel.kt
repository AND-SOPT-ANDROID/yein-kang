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
import org.sopt.and.presentation.my.model.MyState
import org.sopt.and.presentation.my.sideeffect.MySideEffect
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(MyState())
    val state = _state.asStateFlow()

    private val _intent = MutableSharedFlow<MySideEffect>()
    val intent = _intent.asSharedFlow()

    fun updateId(id: String) = _state.update {
        it.copy(id = id)
    }

    fun onLogOutButtonClick() = viewModelScope.launch {
        _intent.emit(MySideEffect.LogOut)
    }


}