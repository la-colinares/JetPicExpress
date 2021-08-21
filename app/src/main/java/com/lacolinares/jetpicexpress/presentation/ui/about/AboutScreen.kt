package com.lacolinares.jetpicexpress.presentation.ui.about

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lacolinares.jetpicexpress.presentation.ui.about.components.AboutBackDropFrontLayer
import com.lacolinares.jetpicexpress.presentation.ui.about.components.AboutBody
import com.lacolinares.jetpicexpress.presentation.ui.about.components.AboutHeader
import com.lacolinares.jetpicexpress.presentation.ui.about.components.AboutTopContent
import com.lacolinares.jetpicexpress.util.extensions.setTransparentStatusBar
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator
import kotlinx.coroutines.launch

private val baseModifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun AboutScreen(
    navigator: AppNavigator
) {
    navigator.activity.setTransparentStatusBar(false)

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val selectedUrl = remember { mutableStateOf("") }
    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = { AboutTopContent(navigator = navigator) },
        backLayerContent = {
            Column(modifier = Modifier.fillMaxSize()
            ) {
                AboutHeader()
                CustomDivider()
                AboutBody() { url ->
                    selectedUrl.value = url
                    scope.launch { scaffoldState.conceal() }
                }
            }
        },
        frontLayerContent = {
            AboutBackDropFrontLayer(url = selectedUrl.value) {
                scope.launch { scaffoldState.reveal() }
            }
        },
        headerHeight = 0.dp,
        backLayerBackgroundColor = MaterialTheme.colors.surface,
        gesturesEnabled = false
    )
}

@Composable
private fun CustomDivider() {
    Divider(thickness = 1.dp, modifier = baseModifier)
}