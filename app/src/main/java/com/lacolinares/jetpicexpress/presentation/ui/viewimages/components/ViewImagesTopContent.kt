package com.lacolinares.jetpicexpress.presentation.ui.viewimages.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200
import com.lacolinares.jetpicexpress.presentation.ui.theme.Teal200
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator

@Composable
fun ViewImagesTopContent(
    navigator: AppNavigator
) {
    TopAppBar(
        title = {
            Text(
                text = "Saved Images",
                color = Light200,
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back_24),
                contentDescription = "back icon",
                tint = Light200,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .clickable {
                        navigator.pop()
                    }
            )
        },
        backgroundColor = Teal200,
        elevation = 4.dp
    )
}