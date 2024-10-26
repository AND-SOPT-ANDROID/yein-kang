package org.sopt.and.home.model

data class HomeState(
    val selectedTabIndex: Int = 0,
    val bannerImageList: List<Int> = emptyList(),
    val recommendedImageList: HomeContent = HomeContent(),
    val rankingImageList: HomeContent = HomeContent(),
)
