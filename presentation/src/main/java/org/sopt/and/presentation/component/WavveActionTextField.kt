package org.sopt.and.presentation.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.presentation.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.ui.theme.White

@Composable
fun WavveActionTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    var isShowPassword by remember { mutableStateOf(false) }

    WavveTextField(
        value = value,
        hint = hint,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        actionButton = {
            Text(
                text = if (isShowPassword) stringResource(R.string.text_field_hide)
                else stringResource(R.string.text_field_show),
                color = White,
                modifier = Modifier
                    .noRippleClickable(
                        onClick = {
                            isShowPassword = !isShowPassword
                        }
                    )
            )
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun WavveActionTextFieldPreview() {
    WavveActionTextField(
        value = "password",
        hint = "hint",
        onValueChange = {}
    )
}