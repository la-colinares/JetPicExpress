package com.lacolinares.jetpicexpress.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.home.components.HomeButtonMenu
import com.lacolinares.jetpicexpress.presentation.ui.home.components.HomeFooterBottomContent
import com.lacolinares.jetpicexpress.presentation.ui.home.components.HomeFooterTopContent
import com.lacolinares.jetpicexpress.presentation.ui.home.components.HomeImageSlider
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200
import com.lacolinares.jetpicexpress.util.extensions.setTransparentStatusBar
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator
import com.lacolinares.jetpicexpress.util.navigation.Screen


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navigator: AppNavigator
) {
    navigator.activity.setTransparentStatusBar()
    Box(
        modifier = Modifier.navigationBarsPadding()
    ) {
        MainContent()
        TopContent(navigator)
        BottomContent(this, navigator)
    }
}

@Composable
private fun BoxScope.TopContent(navigator: AppNavigator) {
    Row(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(top = 48.dp, end = 16.dp)
    ) {
        IconButton(
            onClick = {
                navigator.navigateTo(Screen.AboutScreen.route)
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_info_24),
                contentDescription = "about",
                tint = Light200
            )
        }
    }
}

@Composable
private fun MainContent() {
    HomeImageSlider()
}

@Composable
private fun BottomContent(
    boxScope: BoxScope,
    navigator: AppNavigator
) {
    boxScope.apply {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp)
        ) {
            HomeFooterTopContent()
            Spacer(modifier = Modifier.height(24.dp))
            HomeButtonMenu(navigator = navigator)
            Spacer(modifier = Modifier.height(48.dp))
            HomeFooterBottomContent()
        }
    }
}
