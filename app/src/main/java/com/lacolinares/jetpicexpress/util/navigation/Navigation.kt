package com.lacolinares.jetpicexpress.util.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lacolinares.jetpicexpress.presentation.ui.editimage.EditImageScreen
import com.lacolinares.jetpicexpress.presentation.ui.editimage.EditImageViewModel
import com.lacolinares.jetpicexpress.presentation.ui.home.HomeScreen
import com.lacolinares.jetpicexpress.presentation.ui.splash.SplashScreen

@Composable
fun Navigation(activity: Activity) {
    val navController = rememberNavController()

    val navigator = AppNavigator(
        activity = activity,
        navController = navController
    )

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ){
        composable(Screen.SplashScreen.route){
            SplashScreen(navigator = navigator)
        }
        composable(Screen.HomeScreen.route){
            HomeScreen(navigator = navigator)
        }
        composable(Screen.EditImageScreen.route){
            val editImageViewModel = hiltViewModel<EditImageViewModel>()
            EditImageScreen(viewModel = editImageViewModel, navigator = navigator)
        }
    }
}