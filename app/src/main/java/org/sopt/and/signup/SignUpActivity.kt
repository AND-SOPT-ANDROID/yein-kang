package org.sopt.and.signup

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.sopt.and.R
import org.sopt.and.component.CloseTopBar
import org.sopt.and.signup.intent.SignUpIntent
import org.sopt.and.signup.model.SignUpState
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.FirstGrey
import org.sopt.and.util.KeyUtil.ID
import org.sopt.and.util.KeyUtil.PASSWORD
import org.sopt.and.util.KeyUtil.SNACK_BAR_MESSAGE

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ANDANDROIDTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    topBar = {
                        CloseTopBar(
                            title = stringResource(R.string.signup_top_bar_title),
                            onBtnClick = {
                                finish()
                            }
                        )
                    }
                ) { innerPadding ->

                    val signUpState = remember { mutableStateOf(SignUpState()) }

                    SignUpScreen(
                        state = signUpState.value,
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(color = FirstGrey),
                        onIdChange = {
                            handledIntent(signUpState, SignUpIntent.EnterId(it))
                        },
                        onPasswordChange = {
                            handledIntent(signUpState, SignUpIntent.EnterPassword(it))
                        },
                        onSignUpButtonClick = {
                            handledIntent(signUpState, SignUpIntent.SignUp)
                        }
                    )

                    signUpState.value.snackBarMessage?.let { message ->
                        LaunchedEffect(snackbarHostState) {
                            snackbarHostState.showSnackbar(message)
                            signUpState.value = signUpState.value.copy(snackBarMessage = null)
                        }
                    }
                }
            }
        }
    }

    private fun handledIntent(state: MutableState<SignUpState>, signUpIntent: SignUpIntent) {
        when (signUpIntent) {
            is SignUpIntent.EnterId -> {
                state.value = state.value.copy(id = signUpIntent.id)
            }
            is SignUpIntent.EnterPassword -> {
                state.value = state.value.copy(password = signUpIntent.password)
            }
            is SignUpIntent.SignUp -> {
                val currentState = state.value
                val isIdValid = isValidatedId(currentState.id)
                val isPasswordValid = isValidatedPassword(currentState.password)

                when {
                    !isIdValid -> {
                        state.value = currentState.copy(
                            snackBarMessage = getString(R.string.signup_failure_email_text)
                        )
                    }
                    !isPasswordValid -> {
                        state.value = currentState.copy(
                            snackBarMessage = getString(R.string.signup_failure_password_text)
                        )
                    }
                    else -> {
                        state.value = currentState.copy(
                            isSignUpSuccess = true,
                        )
                        intent.apply {
                            putExtra(ID, currentState.id)
                            putExtra(PASSWORD, currentState.password)
                            putExtra(SNACK_BAR_MESSAGE, getString(R.string.signup_success_text))
                            setResult(Activity.RESULT_OK, this)
                        }
                        finish()
                    }
                }
            }
        }
    }

    private fun isValidatedId(id: String): Boolean{
        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return id.matches(emailPattern.toRegex())
    }

    private fun isValidatedPassword(password: String): Boolean{
        val passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}$"
        return password.matches(passwordPattern.toRegex())
    }
}