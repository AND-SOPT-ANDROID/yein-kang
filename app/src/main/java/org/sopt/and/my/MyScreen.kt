package org.sopt.and.my

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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.component.ContentsView
import org.sopt.and.component.PairTextView
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.my.intent.MyIntent
import org.sopt.and.my.viewmodel.MyViewModel
import org.sopt.and.ui.theme.FirstGrey
import org.sopt.and.ui.theme.SecondGrey
import org.sopt.and.ui.theme.ThirdGrey
import org.sopt.and.ui.theme.White
import org.sopt.and.util.PreferenceUtil

@Composable
fun MyScreen(
    onLogOut: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = viewModel()
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        val state by viewModel.state.collectAsStateWithLifecycle()

        val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current).value
        val context = rememberUpdatedState(LocalContext.current).value
        val snackBarHostState = remember { SnackbarHostState() }

        viewModel.updateId(PreferenceUtil.id)

        LaunchedEffect(viewModel.intent, lifecycleOwner){
            viewModel.intent.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
                .collect{ intent ->
                    when(intent) {
                        MyIntent.LogOut -> {
                            PreferenceUtil.clearIdPassword()
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.my_logout_text)
                            )
                            onLogOut()
                        }
                        is MyIntent.SnackBar -> TODO()
                    }
                }
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
                text = state.id,
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

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    MyScreen()
}