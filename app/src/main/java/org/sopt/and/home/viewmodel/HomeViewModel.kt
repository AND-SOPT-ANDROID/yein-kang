package org.sopt.and.home.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.and.R
import org.sopt.and.home.model.HomeContent
import org.sopt.and.home.model.HomeState

class HomeViewModel: ViewModel() {

    private var _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        initHomeState()
    }

    private fun initHomeState() = _state.update {
        it.copy(
            bannerImageList = listOf(
                R.drawable.home_banner_img1,
                R.drawable.home_banner_img2,
                R.drawable.home_banner_img3,
                R.drawable.home_banner_img4,
                R.drawable.home_banner_img5
            ),
            recommendedImageList = HomeContent(
                title = "믿고 보는 에디터 추천작",
                programList = listOf(
                    R.drawable.home_banner_img1,
                    R.drawable.home_banner_img2,
                    R.drawable.home_banner_img3,
                    R.drawable.home_banner_img4,
                    R.drawable.home_banner_img5,
                    R.drawable.home_banner_img1,
                    R.drawable.home_banner_img2,
                    R.drawable.home_banner_img3,
                    R.drawable.home_banner_img4,
                    R.drawable.home_banner_img5,
                    R.drawable.home_banner_img1,
                    R.drawable.home_banner_img2,
                    R.drawable.home_banner_img3,
                    R.drawable.home_banner_img4,
                    R.drawable.home_banner_img5
                )
            ),
            rankingImageList = HomeContent(
                title = "오늘의 TOP 20",
                programList = listOf(
                R.drawable.home_banner_img1,
                R.drawable.home_banner_img2,
                R.drawable.home_banner_img3,
                R.drawable.home_banner_img4,
                R.drawable.home_banner_img5,
                R.drawable.home_banner_img1,
                R.drawable.home_banner_img2,
                R.drawable.home_banner_img3,
                R.drawable.home_banner_img4,
                R.drawable.home_banner_img5,
                R.drawable.home_banner_img1,
                R.drawable.home_banner_img2,
                R.drawable.home_banner_img3,
                R.drawable.home_banner_img4,
                R.drawable.home_banner_img5,
                R.drawable.home_banner_img1,
                R.drawable.home_banner_img2,
                R.drawable.home_banner_img3,
                R.drawable.home_banner_img4,
                R.drawable.home_banner_img5
                )
            )
        )
    }

    fun updateSelectedTabIndex(index: Int) = _state.update {
        it.copy(selectedTabIndex = index)
    }

}