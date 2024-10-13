package org.sopt.and.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.FirstGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloseTopBar(
    title: String,
    onBtnClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = FirstGrey,
    titleContentColor: Color = Color.White,
    actionIconContentColor: Color = Color.White
    ) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            Text(
                text = title,
                fontSize = 16.sp
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.icon_close),
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 4.dp)
                    .clickable {
                        onBtnClick()
                    }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = titleContentColor,
            actionIconContentColor = actionIconContentColor
        )
    )
}