package com.lacolinares.jetpicexpress.util.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object HomeScreen : Screen("home_screen")
    object AboutScreen : Screen("about_screen")
    object ViewImagesScreen : Screen("view_images")
    object EditImageScreen : Screen("edit_image_screen")
    object SavedFilterImageScreen : Screen("saved_image_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
