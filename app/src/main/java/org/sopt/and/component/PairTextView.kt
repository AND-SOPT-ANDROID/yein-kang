package org.sopt.and.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.ThirdGrey

@Composable
fun PairTextView(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    titleColor: Color = ThirdGrey,
    subTitleColor: Color = Color.White,
) {
    Column(
        modifier = modifier
    ){
        Text(
            text = title,
            color = titleColor,
        )

        Row{
            Text(
                text = subTitle,
                color = subTitleColor,
                modifier = Modifier.padding(top = 1.dp)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = stringResource(R.string.icon_arrow_right),
                tint = titleColor,
            )
        }
    }
}