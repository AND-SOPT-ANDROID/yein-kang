package org.sopt.and.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.sopt.and.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    Text(
        text = stringResource(R.string.main_tab_home)
    )
}