package org.sopt.and.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import org.sopt.and.component.DividerWithText
import org.sopt.and.component.OtherServiceIconRow
import org.sopt.and.component.WavveActionTextField
import org.sopt.and.component.WavveTextField
import org.sopt.and.signin.model.SignInState
import org.sopt.and.ui.theme.SecondGrey
import org.sopt.and.ui.theme.ThirdGrey

@Composable
fun SignInScreen(
    state: SignInState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInButtonClick: () -> Unit,
    onFindInButtonClick: () -> Unit,
    onPasswordResetButtonClick: () -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier
){
    Column(
        modifier = modifier.fillMaxSize()
            .padding(top = 48.dp)
    ){
        WavveTextField(
            value = state.id,
            hint = stringResource(R.string.signup_login_hint),
            onValueChange = onIdChange,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        WavveActionTextField(
            value = state.password,
            hint = stringResource(R.string.signin_password_hint),
            onValueChange = onPasswordChange,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
            ,
            onClick = {
                onSignInButtonClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1453FA)
            )
        ) {
            Text(
                text = stringResource(R.string.signin_login_text),
                fontSize = 16.sp,
                color = Color.White,
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
                    .clickable {
                        onFindInButtonClick()
                    }
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
                    .clickable {
                        onPasswordResetButtonClick()
                    }
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
                    .clickable {
                        onSignUpButtonClick()
                    }
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
    }
}