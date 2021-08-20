package com.lacolinares.jetpicexpress.presentation.ui.editimage.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.base.basecomponents.AppAlertDialog
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200
import com.lacolinares.jetpicexpress.presentation.ui.theme.Teal200
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator

@Composable
fun EditImageTopContent(
    hasFilteredImage: Boolean,
    modifier: Modifier,
    navigator: AppNavigator,
    onSave: () -> Unit
) {
    var isBack by remember { mutableStateOf(false) }
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
            IconButton(
                onClick = {
                    isBack = true
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back_24),
                    contentDescription = "back icon",
                    tint = Light200,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        },
        actions = {
            if (hasFilteredImage) {
                IconButton(
                    onClick = { onSave.invoke() }
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

    if (isBack) {
        if (hasFilteredImage) {
            AppAlertDialog(
                confirmButtonText = "Yes",
                dismissButtonText = "Cancel",
                message = "Do you want to discard changes?",
                onConfirm = {
                    navigator.pop()
                },
                onDismiss = {
                    isBack = false
                }
            )
        } else {
            navigator.pop()
            isBack = false
        }
    }
}