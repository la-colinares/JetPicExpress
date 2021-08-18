package com.lacolinares.jetpicexpress.presentation.ui.editimage

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.lacolinares.jetpicexpress.presentation.ui.editimage.components.EditImageMainContent
import com.lacolinares.jetpicexpress.util.CoroutineThread
import com.lacolinares.jetpicexpress.util.extensions.ShowToast
import com.lacolinares.jetpicexpress.util.extensions.setTransparentStatusBar
import com.lacolinares.jetpicexpress.util.extensions.toBitmap
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator
import com.lacolinares.jetpicexpress.util.navigation.Screen
import jp.co.cyberagent.android.gpuimage.GPUImage

@Composable
fun EditImageScreen(
    viewModel: EditImageViewModel,
    navigator: AppNavigator
) {
    navigator.activity.setTransparentStatusBar(false)

    PickImage(navigator, viewModel)
    SavedFilteredImageObserver(navigator, viewModel)
    BackPressHandler(navigator, viewModel)
}

@Composable
fun PickImage(
    navigator: AppNavigator,
    viewModel: EditImageViewModel
) {
    val context = LocalContext.current

    val gpuImage = GPUImage(context)
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            val bitmap = uri.toBitmap(context = context)
            bitmap?.let {
                imageBitmap = it
            }
        } else {
            navigator.pop()
        }
    }
    LaunchedEffect(key1 = true) {
        CoroutineThread.io {
            imagePicker.launch("image/*")
        }
    }

    imageBitmap?.let {
        viewModel.setFilteredBitmap(it)
        EditImageMainContent(
            originalBitmap = it,
            gpuImage = gpuImage,
            viewModel = viewModel
        )
    }
}

@Composable
fun SavedFilteredImageObserver(
    navigator: AppNavigator,
    viewModel: EditImageViewModel
) {
    val state = viewModel.savedImageFilterUIState.collectAsState()
    with(state.value) {
        val loading by remember { mutableStateOf(isLoading) }
        if (loading) {
            Log.d("Image Status","Saving Image...")
        }
        imageName?.let {
            ShowToast(message = "Saved Image Successfully!")
            val route: String = Screen.SavedFilterImageScreen.withArgs(it)
            navigator.Navigate(route = route)
        } ?: kotlin.run {
            error?.let { e ->
                ShowToast(message = e)
            }
        }
    }
}

@Composable
fun BackPressHandler(
    navigator: AppNavigator,
    viewModel: EditImageViewModel
) {
    val hasFilter = viewModel.hasSelectedFilter.collectAsState().value
    BackHandler {
        if (hasFilter) {
            Log.d("OnBackPressed: ", "(Has Filter: $hasFilter)")
        } else {
            Log.d("OnBackPressed: ", "(Has Filter: $hasFilter)")
            navigator.pop()
        }
    }
}