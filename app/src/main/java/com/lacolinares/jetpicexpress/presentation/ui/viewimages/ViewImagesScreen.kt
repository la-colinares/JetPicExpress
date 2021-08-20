package com.lacolinares.jetpicexpress.presentation.ui.viewimages

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.theme.Teal200
import com.lacolinares.jetpicexpress.presentation.ui.viewimages.components.SavedImageItem
import com.lacolinares.jetpicexpress.presentation.ui.viewimages.components.ViewImagesTopContent
import com.lacolinares.jetpicexpress.util.extensions.ShowToast
import com.lacolinares.jetpicexpress.util.extensions.getFileLastModified
import com.lacolinares.jetpicexpress.util.extensions.setTransparentStatusBar
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun ViewImagesScreen(
    viewModel: ViewImagesViewModel,
    navigator: AppNavigator
) {
    navigator.activity.setTransparentStatusBar(false)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val allImages = viewModel.viewImagesUIState.collectAsState()

    Box {
        if (!allImages.value.images.isNullOrEmpty()) {
            Column {
                ViewImagesTopContent(navigator)

                with(allImages.value) {
                    val loading by remember { mutableStateOf(isLoading) }
                    if (loading) {
                        Log.d("View Images", "Fetching Images...")
                    }

                    images?.let {
                        LazyColumn(state = listState) {
                            items(it) { image ->
                                val bitmap = image.second
                                val title = image.first.name
                                val date = context.getFileLastModified(title)
                                SavedImageItem(
                                    bitmap = bitmap,
                                    title = title,
                                    date = date
                                )
                            }
                        }
                    } ?: kotlin.run {
                        error?.let {
                            ShowToast(message = it)
                        }
                    }
                }
            }
            val isScrolledToBottom by remember {
                derivedStateOf {
                    listState.firstVisibleItemIndex > 0
                }
            }

            if (isScrolledToBottom) {
                AnimatedVisibility(
                    visible = true,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                ) {
                    FloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(index = 0)
                            }
                        },
                        shape = RoundedCornerShape(50),
                        backgroundColor = Teal200,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_up_24),
                            contentDescription = "top_icon"
                        )
                    }
                }
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