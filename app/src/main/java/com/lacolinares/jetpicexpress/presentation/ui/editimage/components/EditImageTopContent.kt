package com.lacolinares.jetpicexpress.presentation.ui.editimage.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200
import com.lacolinares.jetpicexpress.presentation.ui.theme.Teal200

@Composable
fun EditImageTopContent(
    hasFilteredImage: Boolean,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "Apply Filter",
                color = Light200,
                fontSize = 18.sp
            )
        },
        modifier = modifier,
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back_24),
                contentDescription = "back icon",
                tint = Light200,
                modifier = Modifier.padding(start = 12.dp)
            )
        },
        actions = {
            if (hasFilteredImage){
                IconButton(
                    onClick = { onClick.invoke() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check_circle_24),
                        contentDescription = "back icon",
                        tint = Light200,
                    )
                }
            }
        },
        backgroundColor = Teal200,
        elevation = 4.dp
    )
}

@Preview(showSystemUi = false)
@Composable
fun EditImageTopContentPreview() {
    TopAppBar(
        title = {
            Text(
                text = "Apply Filter",
                color = Light200,
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back_24),
                contentDescription = "back icon",
                tint = Light200,
                modifier = Modifier.padding(start = 12.dp)
            )
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_circle_24),
                    contentDescription = "back icon",
                    tint = Light200,
                )
            }
        },
        backgroundColor = Teal200,
        elevation = 4.dp
    )
}