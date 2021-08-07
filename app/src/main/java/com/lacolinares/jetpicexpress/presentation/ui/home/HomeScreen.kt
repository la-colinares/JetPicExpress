package com.lacolinares.jetpicexpress.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.lacolinares.jetpicexpress.presentation.ui.home.components.HomeButtonMenu
import com.lacolinares.jetpicexpress.presentation.ui.home.components.HomeFooterBottomContent
import com.lacolinares.jetpicexpress.presentation.ui.home.components.HomeFooterTopContent
import com.lacolinares.jetpicexpress.presentation.ui.home.components.HomeImageSlider
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    Box {
        MainContent()
        TopContent(this)
        BottomContent(this)
    }
}

@Composable
private fun TopContent(boxScope: BoxScope){
    boxScope.apply {
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 48.dp, end = 16.dp)
        ) {
            Icon(Icons.Rounded.Info, contentDescription = "about", tint = Light200)
        }
    }
}

@Composable
private fun MainContent(){
    HomeImageSlider()
}

@Composable
private fun BottomContent(boxScope: BoxScope) {
    boxScope.apply {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp)
        ) {
            HomeFooterTopContent()
            Spacer(modifier = Modifier.height(24.dp))
            HomeButtonMenu()
            Spacer(modifier = Modifier.height(48.dp))
            HomeFooterBottomContent()
        }
    }
}
