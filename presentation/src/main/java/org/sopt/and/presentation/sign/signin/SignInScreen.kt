package org.sopt.and.presentation.sign.signin

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.presentation.R
import org.sopt.and.presentation.component.BackTopBar
import org.sopt.and.presentation.component.DividerWithText
import org.sopt.and.presentation.component.OtherServiceIconRow
import org.sopt.and.presentation.component.WavveActionTextField
import org.sopt.and.presentation.component.WavveTextField
import org.sopt.and.presentation.delegate.NetworkState
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.sign.signin.sideeffect.SignInSideEffect
import org.sopt.and.presentation.sign.signin.viewmodel.SignInViewModel
import org.sopt.and.presentation.ui.theme.FirstGrey
import org.sopt.and.presentation.ui.theme.SecondGrey
import org.sopt.and.presentation.ui.theme.ThirdGrey
import org.sopt.and.presentation.ui.theme.WavveColor
import org.sopt.and.presentation.ui.theme.White

@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    navigateToMy: () -> Unit,
    onBackButtonClick: () -> Unit,
    onFindInButtonClick: () -> Unit,
    onPasswordResetButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
){

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = rememberUpdatedState(LocalContext.current).value
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.intent) {
       viewModel.intent.collect{ intent ->
           when(intent) {
               SignInSideEffect.SignIn -> {
                   viewModel.saveUser(state.id, state.password)
                   navigateToMy()
               }
               SignInSideEffect.SignUp -> navigateToSignUp()
               is SignInSideEffect.SnackBar -> snackBarHostState.showSnackbar(context.getString(intent.message))
               is SignInSideEffect.SnackBarText -> snackBarHostState.showSnackbar(intent.message)
           }
       }
    }

    LaunchedEffect(viewModel.networkState) {
        collectNetworkState(viewModel)
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
                viewModel.signIn()
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

private suspend fun collectNetworkState(viewModel: SignInViewModel) {
    viewModel.networkState.collect { networkState ->
        when(networkState){
            is NetworkState.Loading -> {
                // TODO 로딩 추가
            }
            is NetworkState.Error -> {
                viewModel.handleSignInIntentError(networkState.title + networkState.msg)
            }
            is NetworkState.Success -> {
                viewModel.handleSignInIntentSuccess()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(){
    Box(
        modifier = Modifier.fillMaxSize().background(FirstGrey)
    ){
        SignInScreen(
            navigateToSignUp = {},
            navigateToMy = {},
            onFindInButtonClick = {},
            onPasswordResetButtonClick = {},
            modifier = Modifier,
            onBackButtonClick = {}
        )
    }
}