package org.sopt.and.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.presentation.R
import org.sopt.and.presentation.component.ContentRow
import org.sopt.and.presentation.component.HorizontalBannerPager
import org.sopt.and.presentation.component.LogoTopBar
import org.sopt.and.presentation.component.RankContentRow
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.home.component.HomeTabRow
import org.sopt.and.presentation.home.viewmodel.HomeViewModel
import org.sopt.and.presentation.ui.theme.FirstGrey

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
){

    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = FirstGrey),
        contentPadding = PaddingValues(8.dp)
    ) {
        item{
            LogoTopBar(
                actions = listOf({
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.cast_icon),
                        contentDescription = stringResource(R.string.icon_cast),
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 8.dp)
                            .noRippleClickable(
                                onClick = {

                                }
                            )
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.live_icon),
                        contentDescription = stringResource(R.string.icon_live),
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 8.dp)
                            .noRippleClickable(
                                onClick = {

                                }
                            )
                    )
                })
            )
        }

        stickyHeader {
            HomeTabRow(
                selectedTabIndex = state.selectedTabIndex,
                onTabClick = viewModel::updateSelectedTabIndex
            )
        }

        item {
            HorizontalBannerPager(
                imageList = state.bannerImageList,
                modifier = Modifier
                    .wrapContentWidth()
            )
        }

        item {
            ContentRow(
                title = state.recommendedImageList.title,
                contentList = state.recommendedImageList.programList
            )
        }

        item{
            RankContentRow(
                title = state.rankingImageList.title,
                contentList = state.rankingImageList.programList
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}