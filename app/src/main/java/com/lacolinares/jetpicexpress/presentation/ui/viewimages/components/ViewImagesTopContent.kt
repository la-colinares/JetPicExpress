package com.lacolinares.jetpicexpress.presentation.ui.viewimages.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.base.basecomponents.BaseTopAppBar
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator

@Composable
fun ViewImagesTopContent(
    navigator: AppNavigator
) {
    BaseTopAppBar(appBarTitle = stringResource(id = R.string.saved_images)) {
        navigator.pop()
    }
}