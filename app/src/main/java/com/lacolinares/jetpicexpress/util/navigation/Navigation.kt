package com.lacolinares.jetpicexpress.util.navigation

import android.app.Activity
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.lacolinares.jetpicexpress.presentation.ui.about.AboutScreen
import com.lacolinares.jetpicexpress.presentation.ui.editimage.EditImageScreen
import com.lacolinares.jetpicexpress.presentation.ui.editimage.EditImageViewModel
import com.lacolinares.jetpicexpress.presentation.ui.home.HomeScreen
import com.lacolinares.jetpicexpress.presentation.ui.savedimage.SavedImageScreen
import com.lacolinares.jetpicexpress.presentation.ui.splash.SplashScreen
import com.lacolinares.jetpicexpress.presentation.ui.viewimages.ViewImagesScreen
import com.lacolinares.jetpicexpress.presentation.ui.viewimages.ViewImagesViewModel
import com.lacolinares.jetpicexpress.util.FileHelper

private const val PARAM_IMAGE_NAME = "imgName"

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    activity: Activity,
    fileHelper: FileHelper,
) {
    val navController = rememberAnimatedNavController()

    val navigator = AppNavigator(
        activity = activity,
        navController = navController
    )
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        customComposable(Screen.SplashScreen.route) {
            SplashScreen(navigator = navigator)
        }
        customComposable(Screen.HomeScreen.route) {
            HomeScreen(navigator = navigator)
        }
        customComposable(Screen.AboutScreen.route){
            AboutScreen(navigator = navigator)
        }
        customComposable(Screen.ViewImagesScreen.route) {
            val viewImagesViewModel = hiltViewModel<ViewImagesViewModel>()
            ViewImagesScreen(viewModel = viewImagesViewModel, navigator = navigator, fileHelper = fileHelper)
        }
        customComposable(Screen.EditImageScreen.route) {
            val editImageViewModel = hiltViewModel<EditImageViewModel>()
            EditImageScreen(viewModel = editImageViewModel, navigator = navigator)
        }
        customComposable(
            route = "${Screen.SavedFilterImageScreen.route}/{$PARAM_IMAGE_NAME}",
            arguments = listOf(
                navArgument(PARAM_IMAGE_NAME) {
                    type = NavType.StringType
                    defaultValue = "empty"
                }
            )
        ) { entry ->
            val imageName = entry.arguments?.getString(PARAM_IMAGE_NAME).orEmpty()
            SavedImageScreen(savedImageName = imageName, navigator = navigator, fileHelper = fileHelper)
        }
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.customComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    val offSetX = 300
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = { _, _ -> slideInHorizontally(
            initialOffsetX = { offSetX },
            animationSpec = tween(
                durationMillis = offSetX,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(animationSpec = tween(offSetX)) },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { -offSetX },
                animationSpec = tween(
                    durationMillis = offSetX,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(offSetX))
        },
        popEnterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -offSetX },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(offSetX))
        },
        popExitTransition = { _, _ -> slideOutHorizontally(
            targetOffsetX = { offSetX },
            animationSpec = tween(
                durationMillis = offSetX,
                easing = FastOutSlowInEasing
            )
        ) + fadeOut(animationSpec = tween(offSetX)) }
    ) {
        content.invoke(it)
    }
}