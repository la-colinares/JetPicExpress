package com.lacolinares.jetpicexpress.presentation.ui.about.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.jetpicexpress.R


@Preview(showBackground = true)
@Composable
fun AboutHeader() {
    val logo = if (isSystemInDarkTheme()) R.drawable.jetpic_light_icon else R.drawable.jetpic_dark_icon
    val size = 80.dp

    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            modifier = Modifier.size(size),
            shape = CircleShape,
            elevation = 12.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Image(
                painter = painterResource(id = logo),
                contentDescription = "about app logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.app_version),
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}