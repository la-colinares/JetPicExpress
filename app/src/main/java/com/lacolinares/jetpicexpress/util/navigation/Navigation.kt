package com.lacolinares.jetpicexpress.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lacolinares.jetpicexpress.presentation.ui.home.HomeScreen
import com.lacolinares.jetpicexpress.presentation.ui.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ){
        composable(Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
    }
}