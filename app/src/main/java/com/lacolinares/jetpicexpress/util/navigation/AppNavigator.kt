package com.lacolinares.jetpicexpress.util.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

open class AppNavigator(
    val activity: Activity,
    private val navController: NavController
) {

    @Composable
    open fun Navigate(route: String, pop: Boolean = false) {
        LaunchedEffect(key1 = true) {
            withContext(Dispatchers.Main) {
                with(navController) {
                    if (pop) popBackStack()
                    navigate(route = route)
                }
            }
        }
    }

    open fun navigateTo(route: String, pop: Boolean = false) {
        with(navController) {
            if (pop) popBackStack()
            navigate(route = route)
        }
    }

    @Composable
    open fun NavigateWithDelay(route: String, delay: Long, pop: Boolean = false) {
        LaunchedEffect(key1 = true) {
            withContext(Dispatchers.Main) {
                with(navController) {
                    delay(delay)
                    if (pop) popBackStack()
                    navigate(route = route)
                }
            }
        }
    }

    open fun pop(){
        navController.popBackStack()
    }
}