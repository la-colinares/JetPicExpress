package com.lacolinares.jetpicexpress.util.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.lacolinares.jetpicexpress.presentation.ui.editimage.EditImageScreen
import com.lacolinares.jetpicexpress.presentation.ui.editimage.EditImageViewModel
import com.lacolinares.jetpicexpress.presentation.ui.home.HomeScreen
import com.lacolinares.jetpicexpress.presentation.ui.savedimage.SavedImageScreen
import com.lacolinares.jetpicexpress.presentation.ui.splash.SplashScreen

private const val PARAM_IMAGE_NAME = "imgName"

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
        composable(
            route = "${Screen.SavedFilterImageScreen.route}/{$PARAM_IMAGE_NAME}",
            arguments = listOf(
                navArgument(PARAM_IMAGE_NAME) {
                    type = NavType.StringType
                    defaultValue = "empty"
                }
            )
        ){ entry ->
            val imageName = entry.arguments?.getString(PARAM_IMAGE_NAME).orEmpty()
            SavedImageScreen(imageName = imageName)
        }
    }
}