package org.sopt.and.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import org.sopt.and.R
import org.sopt.and.component.BackTopBar
import org.sopt.and.my.MyActivity
import org.sopt.and.signin.intent.SignInIntent
import org.sopt.and.signin.model.SignInState
import org.sopt.and.signup.SignUpActivity
import org.sopt.and.signup.model.SignUpState
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.FirstGrey
import org.sopt.and.util.KeyUtil.DEFAULT_STRING
import org.sopt.and.util.KeyUtil.EMAIL
import org.sopt.and.util.KeyUtil.ID
import org.sopt.and.util.KeyUtil.PASSWORD
import org.sopt.and.util.KeyUtil.SNACK_BAR_MESSAGE

class SignInActivity : ComponentActivity() {

    private val signUpState: MutableState<SignUpState> by lazy {
        mutableStateOf(SignUpState())
    }

    private val signUpLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val id = data?.getStringExtra(ID) ?: DEFAULT_STRING
            val password = data?.getStringExtra(PASSWORD) ?: DEFAULT_STRING
            val snackBarMessage = data?.getStringExtra(SNACK_BAR_MESSAGE) ?: DEFAULT_STRING

            signUpState.value = signUpState.value.copy(id = id, password = password, snackBarMessage = snackBarMessage)
        }
    }

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
                        BackTopBar(
                            titleImg = R.drawable.wavve_icon,
                            onButtonClick = {
                                finish()
                            }
                        )
                    }
                ) { innerPadding ->

                    val signInState = remember { mutableStateOf(SignInState()) }

                    SignInScreen(
                        state = signInState.value,
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(color = FirstGrey),
                        onIdChange = {
                            handledIntent(signInState, SignInIntent.EnterId(it))
                        },
                        onPasswordChange = {
                            handledIntent(signInState, SignInIntent.EnterPassword(it))

                        },
                        onSignUpButtonClick = {
                            signUpLauncher.launch(Intent(this, SignUpActivity::class.java))
                        },
                        onSignInButtonClick = {
                            handledIntent(signInState, SignInIntent.SignIn)
                        },
                        onFindInButtonClick = {

                        },
                        onPasswordResetButtonClick = {

                        }
                    )

                    if (signUpState.value.snackBarMessage != null) {
                        signInState.value = signInState.value.copy(snackBarMessage = signUpState.value.snackBarMessage)
                        signUpState.value = signUpState.value.copy(snackBarMessage = null)
                    }

                    signInState.value.snackBarMessage?.let { message ->
                        LaunchedEffect(snackbarHostState) {
                            snackbarHostState.showSnackbar(message)
                            signInState.value = signInState.value.copy(snackBarMessage = null)
                        }
                    }

                }
            }
        }
    }

    private fun handledIntent(state: MutableState<SignInState>, intent: SignInIntent) {
        when (intent) {
            is SignInIntent.EnterId -> {
                state.value = state.value.copy(id = intent.id)
            }
            is SignInIntent.EnterPassword -> {
                state.value = state.value.copy(password = intent.password)
            }
            is SignInIntent.SignIn -> {
                if (isValidatedSignIn(state.value.id, state.value.password)) {
                    state.value = state.value.copy(
                        isSignInSuccess = true,
                        snackBarMessage = getString(R.string.signin_success_text)
                    )

                    Intent(this, MyActivity::class.java).apply {
                        putExtra(EMAIL, state.value.id)
                        putExtra(SNACK_BAR_MESSAGE, state.value.snackBarMessage)
                        startActivity(this)
                        finish()
                    }

                } else {
                    state.value = state.value.copy(
                        snackBarMessage = getString(R.string.signin_failure_text)
                    )
                }

            }
        }
    }

    private fun isValidatedSignIn(id: String, password: String): Boolean {
        if (id.isBlank() || password.isBlank()) {
            return false
        }
        return signUpState.let {
            it.value.id == id && it.value.password == password
        }
    }


}