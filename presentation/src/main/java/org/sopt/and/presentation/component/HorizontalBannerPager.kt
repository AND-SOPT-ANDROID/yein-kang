package org.sopt.and.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.and.presentation.R
import org.sopt.and.presentation.ui.theme.FirstGrey

@Composable
fun HorizontalBannerPager(
    @DrawableRes imageList: List<Int>,
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 5,
        pageCount = { 100 }
    )

    LaunchedEffect(true){
        while(true){
            delay(3000)
            coroutineScope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        beyondViewportPageCount = 1,
        modifier = modifier.height(440.dp),
        contentPadding = PaddingValues(20.dp),
        pageSpacing = 8.dp
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = imageList[it % imageList.size]),
                contentDescription = stringResource(R.string.home_banner_image),
                modifier = modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = stringResource(
                    id = R.string.home_banner_indicator,
                    formatArgs = arrayOf((it % imageList.size + 1), imageList.size)
                ),
                color = White,
                fontSize = 10.sp,
                modifier = Modifier
                    .padding(12.dp)
                    .background(
                        color = FirstGrey,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HorizontalBannerPagerPreview() {
    HorizontalBannerPager(
        imageList = listOf(
            R.drawable.home_banner_img1,
            R.drawable.home_banner_img2,
            R.drawable.home_banner_img3,
            R.drawable.home_banner_img4,
        )
    )
}