package com.lacolinares.jetpicexpress.presentation.ui.about.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.base.basecomponents.BaseTopAppBar
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator

@Composable
fun AboutTopContent(
    navigator: AppNavigator
) {
    BaseTopAppBar(appBarTitle = stringResource(id = R.string.about_title)) {
        navigator.pop()
    }
}