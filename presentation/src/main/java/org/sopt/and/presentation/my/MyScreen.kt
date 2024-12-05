package org.sopt.and.presentation.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.and.presentation.R
import org.sopt.and.presentation.component.ContentsView
import org.sopt.and.presentation.component.PairTextView
import org.sopt.and.presentation.delegate.NetworkState
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.my.sideeffect.MySideEffect
import org.sopt.and.presentation.my.viewmodel.MyViewModel
import org.sopt.and.presentation.ui.theme.FirstGrey
import org.sopt.and.presentation.ui.theme.SecondGrey
import org.sopt.and.presentation.ui.theme.ThirdGrey
import org.sopt.and.presentation.ui.theme.White
import org.sopt.and.presentation.util.KeyUtil.DEFAULT_STRING

@Composable
fun MyScreen(
    onLogout: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel.state) {
        if(viewModel.state.value.hobby == DEFAULT_STRING) viewModel.getMyHobby()
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        val state by viewModel.state.collectAsStateWithLifecycle()

        val lifecycleOwner = LocalLifecycleOwner.current
        val context = LocalContext.current
        val snackBarHostState = remember { SnackbarHostState() }

        LaunchedEffect(viewModel.intent, lifecycleOwner){
            viewModel.intent.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
                .collect{ intent ->
                    when(intent) {
                        MySideEffect.Logout -> {
                            viewModel.clearUserPreference()
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.my_logout_text)
                            )
                            onLogout()
                        }
                        is MySideEffect.SnackBar -> snackBarHostState.showSnackbar(context.getString(intent.message))
                        is MySideEffect.SnackBarText -> snackBarHostState.showSnackbar(intent.message)
                    }
                }
        }

        LaunchedEffect(viewModel.networkState, lifecycleOwner) {
           collectNetworkState(viewModel)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SecondGrey)
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp)
            )

            Spacer(Modifier.width(12.dp))

            Text(
                text = state.hobby,
                color = White
            )

            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = stringResource(R.string.icon_notification),
                tint = White
            )

            Spacer(Modifier.width(24.dp))

            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = stringResource(R.string.icon_settings),
                tint = White
            )

        }

        PairTextView(
            title = stringResource(R.string.my_first_buy_text),
            subTitle = stringResource(R.string.my_buy_text),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SecondGrey)
                .padding(start = 12.dp, top = 16.dp, bottom = 16.dp)
        )

        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = FirstGrey)
        )

        PairTextView(
            title = stringResource(R.string.my_ticket_text),
            subTitle = stringResource(R.string.my_buy_text),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SecondGrey)
                .padding(start = 12.dp, top = 16.dp, bottom = 16.dp)
        )

        ContentsView(
            title = stringResource(R.string.my_watch_contents_text),
            contentText = stringResource(R.string.my_watch_contents_empty_text),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = FirstGrey)
                .padding(16.dp)
        )

        ContentsView(
            title = stringResource(R.string.my_like_contents_text),
            contentText = stringResource(R.string.my_like_contents_empty_text),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = FirstGrey)
                .padding(16.dp)
        )

        SnackbarHost(
            hostState = snackBarHostState
        )

        Text(
            text = stringResource(R.string.my_logout_text),
            color = ThirdGrey,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .noRippleClickable(
                    onClick = {
                        viewModel.onLogOutButtonClick()
                    }
                )
        )
    }
}

private suspend fun collectNetworkState(viewModel: MyViewModel) {
    viewModel.networkState.collect { networkState ->
        when(networkState){
            is NetworkState.Loading -> {
                // TODO 로딩 추가
            }
            is NetworkState.Error -> {
                viewModel.handleMyIntentError(networkState.title + networkState.msg)
            }
            is NetworkState.Success -> {
                // TODO 성공 관련 로직 추가
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    MyScreen()
}