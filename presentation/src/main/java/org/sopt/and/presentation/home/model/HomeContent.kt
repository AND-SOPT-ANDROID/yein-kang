package org.sopt.and.presentation.home.model

import org.sopt.and.presentation.util.KeyUtil.DEFAULT_STRING

data class HomeContent(
    val title: String = DEFAULT_STRING,
    val programList: List<Int> = emptyList()
)
