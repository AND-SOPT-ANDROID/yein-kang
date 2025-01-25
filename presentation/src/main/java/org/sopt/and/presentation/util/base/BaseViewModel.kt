package org.sopt.and.presentation.util.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State: UiState, Event: UiEvent, SideEffect: UiSideEffect>() : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()
    val currentState: State
        get() = _uiState.value

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _sideEffect: MutableSharedFlow<SideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun setState(reduce: State.() -> State) {
        _uiState.value = currentState.reduce()
    }

    open fun setEvent(event: Event) {
        dispatchEvent(event)
    }

    private fun dispatchEvent(event: Event) = viewModelScope.launch {
        handleEvent(event)
    }

    protected abstract suspend fun handleEvent(event: Event)

    fun setSideEffect(sideEffect: SideEffect) {
        viewModelScope.launch { _sideEffect.emit(sideEffect) }
    }

}