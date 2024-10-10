package org.sopt.and.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.SecondGrey
import org.sopt.and.ui.theme.ThirdGrey

@Composable
fun WavveTextField(
    value: String,
    hint: String,
    valueColor: Color = Color.White,
    hintColor: Color = ThirdGrey,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    actionButton: @Composable () -> Unit = {},
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = SecondGrey,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        color = hintColor,
                        style = TextStyle(textAlign = TextAlign.Start)
                    )
                }
                BasicTextField(
                    maxLines = 1,
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(
                        color = valueColor,
                        textAlign = TextAlign.Start
                    ),
                    keyboardOptions = keyboardOptions,
                    visualTransformation = visualTransformation
                )
            }
            actionButton()
        }
    }
}

