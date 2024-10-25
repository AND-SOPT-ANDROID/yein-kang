package org.sopt.and.my

import android.content.Intent
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.sopt.and.R
import org.sopt.and.signin.SignInActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.FirstGrey
import org.sopt.and.util.PreferenceUtil

class MyActivity : ComponentActivity() {

    private val preferenceUtil by lazy {
        PreferenceUtil(applicationContext)
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
                ) { innerPadding ->

                    MyScreen(
                        email = preferenceUtil.id,
                        modifier = Modifier
                            .background(color = FirstGrey)
                            .padding(innerPadding),
                        onLogoutClick = {
                            preferenceUtil.clearIdPassword()
                            finish()
                            startActivity(Intent(this, SignInActivity::class.java))
                        }
                    )

                    if(preferenceUtil.id.isBlank()){
                        LaunchedEffect(snackbarHostState) {
                            snackbarHostState.showSnackbar(
                                getString(R.string.my_error_text))
                        }
                    }
                }
            }
        }
    }
}