package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.FacebookColor
import org.sopt.and.ui.theme.KakaoColor
import org.sopt.and.ui.theme.NaverColor
import org.sopt.and.ui.theme.TworldColor

@Composable
fun OtherServiceIconRow(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(KakaoColor)
        ) {
            Image(
                painter = painterResource(id = R.drawable.kakao_icon),
                contentDescription = stringResource(R.string.icon_kakao),
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(TworldColor)
        ) {
            Image(
                painter = painterResource(id = R.drawable.tworld_icon),
                contentDescription = stringResource(R.string.icon_tworld),
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(NaverColor)
        ) {
            Image(
                painter = painterResource(id = R.drawable.naver_icon),
                contentDescription = stringResource(R.string.icon_naver),
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(FacebookColor)
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook_icon),
                contentDescription = stringResource(R.string.icon_facebook),
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.apple_icon),
                contentDescription = stringResource(R.string.icon_apple),
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }
    }
}
