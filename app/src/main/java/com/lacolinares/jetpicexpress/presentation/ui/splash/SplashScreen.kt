package com.lacolinares.jetpicexpress.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.util.Constants
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator
import com.lacolinares.jetpicexpress.util.navigation.Screen

@Composable
fun SplashScreen(
    navigator: AppNavigator
) {
    navigator.NavigateWithDelay(
        route = Screen.HomeScreen.route,
        delay = Constants.SPLASH_SCREEN_DELAY,
        pop = true
    )

    DisplayLogo()
}

@Composable
private fun DisplayLogo() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val logo = if (isSystemInDarkTheme()) R.drawable.jetpic_light_icon else R.drawable.jetpic_dark_icon
        val size = 128.dp
        Image(
            painter = painterResource(id = logo),
            contentDescription = "app logo",
            modifier = Modifier
                .width(size)
                .height(size)
        )
    }
}