package com.lacolinares.jetpicexpress.presentation.ui.savedimage.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.base.basecomponents.Button
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator

@Composable
fun SavedImageBottomContent(
    modifier: Modifier,
    navigator: AppNavigator
){
    Button(
        text = "Back to Home",
        buttonBackgroundColor = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.white),
        ),
        modifier = modifier
            .padding(end = 8.dp)
    ) {
        navigator.pop()
    }
}