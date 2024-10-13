package org.sopt.and.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.FirstGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackTopBar(
    @DrawableRes titleImg: Int,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = FirstGrey,
    titleContentColor: Color = Color.White,
    navigationIconContentColor: Color = Color.White,
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            Image(
                imageVector = ImageVector.vectorResource(id = titleImg),
                contentDescription = stringResource(R.string.logo_image),
                modifier = Modifier.height(32.dp)
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.icon_close),
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 4.dp)
                    .clickable {
                        onButtonClick()
                    }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = titleContentColor,
            navigationIconContentColor = navigationIconContentColor,
        )
    )
}