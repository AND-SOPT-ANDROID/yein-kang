package org.sopt.and.sign.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.component.BackTopBar
import org.sopt.and.component.DividerWithText
import org.sopt.and.component.OtherServiceIconRow
import org.sopt.and.component.WavveActionTextField
import org.sopt.and.component.WavveTextField
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.sign.signin.intent.SignInIntent
import org.sopt.and.sign.signin.viewmodel.SignInViewModel
import org.sopt.and.ui.theme.FirstGrey
import org.sopt.and.ui.theme.SecondGrey
import org.sopt.and.ui.theme.ThirdGrey
import org.sopt.and.ui.theme.WavveColor
import org.sopt.and.ui.theme.White
import org.sopt.and.util.PreferenceUtil

@Composable
fun SignInScreen(
    signUpId: String,
    signUpPassword: String,
    navigateToSignUp: () -> Unit,
    navigateToMy: () -> Unit,
    onBackButtonClick: () -> Unit,
    onFindInButtonClick: () -> Unit,
    onPasswordResetButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = viewModel()
){

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = rememberUpdatedState(LocalContext.current).value
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.intent) {
       viewModel.intent.collect{ intent ->
           when(intent) {
               SignInIntent.SignIn -> {
                   PreferenceUtil.id = state.id
                   PreferenceUtil.password = state.password
                   navigateToMy()
               }
               SignInIntent.SignUp -> navigateToSignUp()
               is SignInIntent.SnackBar -> {
                   snackBarHostState.showSnackbar(context.getString(intent.message))
               }
           }
       }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ){
        BackTopBar(
            titleImg = R.drawable.wavve_icon,
            onButtonClick = {
                onBackButtonClick()
            }
        )

        Spacer(modifier = Modifier.height(48.dp))

        WavveTextField(
            value = state.id,
            hint = stringResource(R.string.signup_login_hint),
            onValueChange = viewModel::updateId,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        WavveActionTextField(
            value = state.password,
            hint = stringResource(R.string.signin_password_hint),
            onValueChange = viewModel::updatePassword,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
            ,
            onClick = {
                viewModel.onSignInButtonClick(signUpId, signUpPassword)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = WavveColor
            )
        ) {
            Text(
                text = stringResource(R.string.signin_login_text),
                fontSize = 16.sp,
                color = White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ){
            Text(
                text = stringResource(R.string.signin_find_id_text),
                fontSize = 12.sp,
                color = ThirdGrey,
                modifier = Modifier
                    .noRippleClickable(
                        onClick = onFindInButtonClick
                    )
            )

            Text(
                text = stringResource(R.string.signin_divider_text),
                fontSize = 12.sp,
                color = SecondGrey,
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                text = stringResource(R.string.signin_reset_password_text),
                fontSize = 12.sp,
                color = ThirdGrey,
                modifier = Modifier
                    .noRippleClickable(
                        onClick = onPasswordResetButtonClick
                    )
            )

            Text(
                text = stringResource(R.string.signin_divider_text),
                fontSize = 12.sp,
                color = SecondGrey,
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                text = stringResource(R.string.signin_signup_text),
                fontSize = 12.sp,
                color = ThirdGrey,
                modifier = Modifier
                    .noRippleClickable(
                        onClick = {
                            viewModel.onSignUpButtonClick()
                        }
                    )
            )
        }

        Spacer(modifier = Modifier.padding(32.dp))

        DividerWithText(
            text = stringResource(R.string.signin_other_service_text),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.padding(12.dp))

        OtherServiceIconRow(modifier = Modifier.padding(horizontal = 8.dp))

        Text(
            text = stringResource(R.string.signin_other_service_guide),
            fontSize = 12.sp,
            color = ThirdGrey,
            modifier = Modifier.padding(top = 32.dp, start = 8.dp, end = 8.dp)
        )

        Spacer(Modifier.weight(1f))

        SnackbarHost(
            hostState = snackBarHostState,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(){
    Box(
        modifier = Modifier.fillMaxSize().background(FirstGrey)
    ){
        SignInScreen(
            signUpId = "",
            signUpPassword = "",
            navigateToSignUp = {},
            navigateToMy = {},
            onFindInButtonClick = {},
            onPasswordResetButtonClick = {},
            modifier = Modifier,
            onBackButtonClick = {}
        )
    }
}