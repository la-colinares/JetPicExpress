package com.lacolinares.jetpicexpress.presentation.base.basecomponents

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.theme.Shapes

@Composable
fun AppAlertDialog(
    confirmButtonText : String,
    dismissButtonText : String,
    message : String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    var show by remember { mutableStateOf(true) }
    if (show) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(stringResource(id = R.string.app_name))
            },
            shape = Shapes.large,
            text = {
                Text(text = message)
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm.invoke()
                        show = false
                    }) {
                    Text(
                        text = confirmButtonText,
                        color = MaterialTheme.colors.secondary
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss.invoke()
                        show = false
                    }) {
                    Text(
                        text = dismissButtonText,
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        )
    }
}