package org.sopt.and.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.SecondGrey
import org.sopt.and.ui.theme.ThirdGrey

@Composable
fun DividerWithText(
    text: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(
            modifier = Modifier
                .height(0.5.dp)
                .weight(1f)
                .background(color = SecondGrey)
        )

        Text(
            text = text,
            color = ThirdGrey,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .height(0.5.dp)
                .weight(1f)
                .background(color = SecondGrey)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DividerWithTextPreview(){
    DividerWithText(
        text = "홈"
    )
}