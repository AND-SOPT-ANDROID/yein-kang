package org.sopt.and.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.component.ContentsView
import org.sopt.and.component.PairTextView
import org.sopt.and.ui.theme.FirstGrey
import org.sopt.and.ui.theme.SecondGrey

@Composable
fun MyScreen(
    email: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SecondGrey)
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp)
            )

            Spacer(Modifier.width(12.dp))

            Text(
                text = email,
                color = Color.White
            )

            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = stringResource(R.string.icon_notification),
                tint = Color.White
            )

            Spacer(Modifier.width(24.dp))

            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = stringResource(R.string.icon_settings),
                tint = Color.White
            )

        }

        PairTextView(
            title = stringResource(R.string.my_first_buy_text),
            subTitle = stringResource(R.string.my_buy_text),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SecondGrey)
                .padding(start = 12.dp, top = 16.dp, bottom = 16.dp)
        )

        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = FirstGrey)
        )

        PairTextView(
            title = stringResource(R.string.my_ticket_text),
            subTitle = stringResource(R.string.my_buy_text),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SecondGrey)
                .padding(start = 12.dp, top = 16.dp, bottom = 16.dp)
        )

        ContentsView(
            title = stringResource(R.string.my_watch_contents_text),
            contentText = stringResource(R.string.my_watch_contents_empty_text),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = FirstGrey)
                .padding(16.dp)
        )

        ContentsView(
            title = stringResource(R.string.my_like_contents_text),
            contentText = stringResource(R.string.my_like_contents_empty_text),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = FirstGrey)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    MyScreen(
        email = "wavve@example.com"
    )
}