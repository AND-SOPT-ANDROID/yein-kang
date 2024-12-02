package org.sopt.and.presentation.home.model

data class HomeState(
    val selectedTabIndex: Int = 0,
    val bannerImageList: List<Int> = emptyList(),
    val recommendedImageList: HomeContent = HomeContent(),
    val rankingImageList: HomeContent = HomeContent(),
)
