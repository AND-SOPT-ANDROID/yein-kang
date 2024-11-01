package org.sopt.and.sign.signup

import androidx.compose.foundation.background
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.component.CloseTopBar
import org.sopt.and.component.DividerWithText
import org.sopt.and.component.OtherServiceIconRow
import org.sopt.and.component.WavveActionTextField
import org.sopt.and.component.WavveTextField
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.sign.signup.intent.SignUpIntent
import org.sopt.and.sign.signup.viewmodel.SignUpViewModel
import org.sopt.and.ui.theme.ThirdGrey
import org.sopt.and.ui.theme.White


@Composable
fun SignUpScreen(
    navigationToSignIn: (String, String) -> Unit,
    onCloseButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel()
) {

    val state by viewModel.state.collectAsState()

    val context = rememberUpdatedState(LocalContext.current).value
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.intent) {
        viewModel.intent.collect {  intent ->
            when(intent) {
                SignUpIntent.SignUp -> {
                    navigationToSignIn(state.id, state.password)
                }
                is SignUpIntent.SnackBar -> {
                    snackBarHostState.showSnackbar(context.getString(intent.message))
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        CloseTopBar(
            title = stringResource(R.string.signup_top_bar_title),
            onBtnClick = {
                onCloseButtonClick()
            }
        )

        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.signup_intro_text))
                addStyle(
                    style = SpanStyle(color = White),
                    start = 0,
                    end = 9
                )
                addStyle(
                    style = SpanStyle(color = ThirdGrey),
                    start = 9,
                    end = 12
                )
                addStyle(
                    style = SpanStyle(color = White),
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
            onValueChange = { viewModel.updateId(it) },
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
            onValueChange = { viewModel.updatePassword(it) },
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

        SnackbarHost(
            hostState = snackBarHostState,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = ThirdGrey)
                .noRippleClickable(
                    onClick = {
                        viewModel.onSignUpButtonClick()
                    }
                )
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.signup_button_text),
                fontSize = 16.sp,
                color = White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
        SignUpScreen(
            navigationToSignIn = { id, password ->

            },
            onCloseButtonClick = {

            }
        )
}