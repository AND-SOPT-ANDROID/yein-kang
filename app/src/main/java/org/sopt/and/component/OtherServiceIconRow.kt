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
import androidx.compose.ui.unit.dp
import org.sopt.and.R

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
                .background(Color(0xFFFFEC00))
        ) {
            Image(
                painter = painterResource(id = R.drawable.kakao_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFF3617CE))
        ) {
            Image(
                painter = painterResource(id = R.drawable.tworld_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFF23B909))
        ) {
            Image(
                painter = painterResource(id = R.drawable.naver_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFF3B5998))
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook_icon),
                contentDescription = null,
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
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }
    }
}
