package org.sopt.and.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.component.ContentRow
import org.sopt.and.component.HorizontalBannerPager
import org.sopt.and.component.LogoTopBar
import org.sopt.and.component.RankContentRow
import org.sopt.and.home.component.HomeTabRow
import org.sopt.and.home.viewmodel.HomeViewModel
import org.sopt.and.ui.theme.FirstGrey

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
){

    val state by viewModel.state.collectAsState()

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
                            .clickable {

                            }
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.live_icon),
                        contentDescription = stringResource(R.string.icon_live),
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 8.dp)
                            .clickable {

                            }
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