package org.sopt.and.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.ThirdGrey

@Composable
fun InfoWithText(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    color: Color = ThirdGrey
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = stringResource(R.string.icon_info),
            tint = color,
            modifier = modifier.height(24.dp)
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = stringResource(textRes),
            fontSize = 12.sp,
            color = color
        )
    }
}

@Preview
@Composable
fun InfoWithTextPreview() {
    InfoWithText(textRes = R.string.signup_login_guide)
}