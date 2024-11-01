package org.sopt.and.home.model

import org.sopt.and.util.KeyUtil.DEFAULT_STRING

data class HomeContent(
    val title: String = DEFAULT_STRING,
    val programList: List<Int> = emptyList()
)
