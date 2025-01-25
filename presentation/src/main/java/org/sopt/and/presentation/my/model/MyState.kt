package org.sopt.and.presentation.my.model

import org.sopt.and.presentation.util.KeyUtil.DEFAULT_STRING
import org.sopt.and.presentation.util.base.UiState

data class MyState(
    val hobby: String = DEFAULT_STRING
): UiState