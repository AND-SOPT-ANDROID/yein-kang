package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.White

@Composable
fun RankContentRow(
    title: String,
    modifier: Modifier = Modifier,
    contentList: List<Int> = emptyList(),
    contentColor: Color = White,
    onRightArrowClick: () -> Unit = {},
    onItemClick: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.icon_arrow_right),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onRightArrowClick()
                    }

            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(contentList) { index, content ->
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .clickable {
                            onItemClick(index)
                        },
                    contentAlignment = Alignment.BottomStart
                ) {
                    Image(
                        painter = painterResource(id = content),
                        // 추후 수정 예정
                        contentDescription = "프로그램 이름",
                        modifier = modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onItemClick(index) }
                    )

                    Text(
                        text = (index + 1).toString(),
                        color = contentColor,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RankContentRowPreview() {
    RankContentRow(
        title = "믿고 보는 웨이브 추천작",
        contentList = listOf(
            R.drawable.home_banner_img1,
            R.drawable.home_banner_img2,
            R.drawable.home_banner_img3,
            R.drawable.home_banner_img4,
            R.drawable.home_banner_img5
        )
    )
}