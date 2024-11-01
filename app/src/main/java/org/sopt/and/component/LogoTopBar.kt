package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.FirstGrey
import org.sopt.and.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoTopBar(
    actions: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier
){
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.wavve_icon),
                contentDescription = stringResource(R.string.logo_image),
                modifier = Modifier.height(32.dp)
            )
        },
        actions = {
            actions.forEach { it() }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = FirstGrey,
            titleContentColor = White,
            actionIconContentColor = White
        )
    )

}

@Preview(showBackground = true)
@Composable
fun LogoTopBarPreview() {
    LogoTopBar(
        actions = listOf()
    )
}