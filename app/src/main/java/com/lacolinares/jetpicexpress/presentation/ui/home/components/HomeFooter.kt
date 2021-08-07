package com.lacolinares.jetpicexpress.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.jetpicexpress.R

@Composable
fun HomeFooterBottomContent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            FooterText("Powered by: ")
            Image(
                painter = painterResource(id = R.drawable.jetpack_compose_icon),
                contentDescription = "jetpack icon",
                modifier = Modifier
                    .height(22.dp)
                    .width(22.dp)
                    .padding(start = 4.dp, end = 4.dp)
            )
            FooterText("JetPack Compose")
        }
    }
}

@Composable
fun HomeFooterTopContent() {
    Text(
        text = stringResource(id = R.string.app_name),
        color = Color.White,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = stringResource(id = R.string.app_description),
        color = Color.White,
        fontSize = 16.sp
    )
}

@Composable
private fun FooterText(
    text: String
){
    Text(
        text = text,
        color = Color.White,
        fontSize = 11.sp
    )
}