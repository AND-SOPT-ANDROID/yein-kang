package org.sopt.and.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.component.DividerWithText
import org.sopt.and.component.OtherServiceIconRow
import org.sopt.and.component.WavveActionTextField
import org.sopt.and.component.WavveTextField
import org.sopt.and.signup.model.SignUpState
import org.sopt.and.ui.theme.ThirdGrey

@Composable
fun SignUpScreen(
    state: SignUpState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.signup_intro_text))
                addStyle(
                    style = SpanStyle(color = Color.White),
                    start = 0,
                    end = 9
                )
                addStyle(
                    style = SpanStyle(color = ThirdGrey),
                    start = 9,
                    end = 12
                )
                addStyle(
                    style = SpanStyle(color = Color.White),
                    start = 13,
                    end = 24
                )
                addStyle(
                    style = SpanStyle(color = ThirdGrey),
                    start = 25,
                    end = 29
                )
            },
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp, start = 8.dp, end = 8.dp)
        )

        WavveTextField(
            value = state.id,
            hint = stringResource(R.string.signup_login_hint),
            onValueChange = onIdChange,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = stringResource(R.string.icon_info),
                tint = ThirdGrey,
                modifier = Modifier.height(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.signup_login_guide),
                fontSize = 12.sp,
                color = ThirdGrey
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        WavveActionTextField(
            value = state.password,
            hint = stringResource(R.string.signup_password_hint),
            onValueChange = onPasswordChange,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = stringResource(R.string.icon_info),
                tint = ThirdGrey,
                modifier = Modifier.height(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.signup_password_guide),
                fontSize = 12.sp,
                color = ThirdGrey
            )
        }

        Spacer(modifier = Modifier.padding(32.dp))

        DividerWithText(
            text = stringResource(R.string.signup_other_service_text),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.padding(12.dp))

        OtherServiceIconRow(modifier = Modifier.padding(horizontal = 8.dp))

        Text(
            text = stringResource(R.string.signup_other_service_guide),
            fontSize = 12.sp,
            color = ThirdGrey,
            modifier = Modifier.padding(top = 32.dp, start = 8.dp, end = 8.dp)
        )

        Spacer(Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = ThirdGrey)
                .clickable {
                    onSignUpButtonClick()
                }
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.signup_button_text),
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}