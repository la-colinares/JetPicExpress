package com.lacolinares.jetpicexpress.presentation.ui.viewimages

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lacolinares.jetpicexpress.presentation.ui.viewimages.components.SavedImageFab
import com.lacolinares.jetpicexpress.presentation.ui.viewimages.components.SavedImageSwipeToDelete
import com.lacolinares.jetpicexpress.presentation.ui.viewimages.components.ViewImagesTopContent
import com.lacolinares.jetpicexpress.util.FileHelper
import com.lacolinares.jetpicexpress.util.extensions.ShowToast
import com.lacolinares.jetpicexpress.util.extensions.setTransparentStatusBar
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ViewImagesScreen(
    viewModel: ViewImagesViewModel,
    fileHelper: FileHelper,
    navigator: AppNavigator
) {
    navigator.activity.setTransparentStatusBar(false)

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val allImages = viewModel.viewImagesUIState.collectAsState()

    Box {
        if (!allImages.value.images.isNullOrEmpty()) {
            Column {
                ViewImagesTopContent(navigator)
                with(allImages.value) {
                    val loading by remember { mutableStateOf(isLoading) }
                    if (loading) Log.d("View Images", "Fetching Images...")

                    images?.let { imageItems ->
                        LazyColumn(state = listState) {
                            itemsIndexed(
                                items = imageItems,
                                key = { _, item -> item.hashCode() }
                            ) { _, image ->
                                val fileName = image.first.name
                                val state = rememberDismissState(
                                    confirmStateChange = {
                                        if (it == DismissValue.DismissedToStart) viewModel.removeFile(fileName)
                                        true
                                    }
                                )

                                val bitmap = image.second
                                val date = fileHelper.getFileLastModified(fileName)

                                SavedImageSwipeToDelete(
                                    bitmap = bitmap,
                                    title = fileName,
                                    date = date,
                                    state = state
                                )
                            }
                        }
                    } ?: kotlin.run {
                        error?.let { ShowToast(message = it) }
                    }
                }
            }
            val isScrolledToBottom by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

            if (isScrolledToBottom) {
                val modifier = Modifier.align(Alignment.BottomEnd)
                SavedImageFab(
                    modifier = modifier,
                    listState = listState,
                    coroutineScope = coroutineScope
                )
            }
        } else {
            ViewImagesTopContent(navigator)
            Text(
                text = "No Record Found",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}