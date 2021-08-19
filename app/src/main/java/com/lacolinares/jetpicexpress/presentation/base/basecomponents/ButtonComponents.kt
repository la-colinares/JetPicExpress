package com.lacolinares.jetpicexpress.presentation.base.basecomponents

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.theme.Dark200

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Dark200,
    buttonBackgroundColor: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorResource(id = R.color.white)
    ),
    fontSize: Int = 14,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(50),
        modifier = modifier,
        colors = buttonBackgroundColor
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = fontSize.sp,
            modifier = Modifier.padding(4.dp)
        )
    }
}