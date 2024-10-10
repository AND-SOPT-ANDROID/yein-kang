package org.sopt.and.my

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
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.FirstGrey

class MyActivity : ComponentActivity() {


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
                        email = intent.getStringExtra("email")
                            ?: getString(R.string.my_email_error_text),
                        modifier = Modifier
                            .background(color = FirstGrey)
                            .padding(innerPadding)
                    )

                    LaunchedEffect(snackbarHostState) {
                        snackbarHostState.showSnackbar(
                            intent.getStringExtra("snackBarMessage")
                                ?: getString(R.string.my_error_text))
                    }
                }
            }
        }
    }
}