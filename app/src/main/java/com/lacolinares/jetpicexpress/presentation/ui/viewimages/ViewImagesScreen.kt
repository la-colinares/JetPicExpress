package com.lacolinares.jetpicexpress.presentation.ui.viewimages

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200
import com.lacolinares.jetpicexpress.presentation.ui.theme.Teal200
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

    Box {
        Column {
            ViewImagesTopContent(navigator)
            val allImages = viewModel.viewImagesUIState.collectAsState()
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
                            SavedImages(
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
    }
}

@Composable
fun ViewImagesTopContent(
    navigator: AppNavigator
) {
    TopAppBar(
        title = {
            Text(
                text = "Saved Images",
                color = Light200,
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back_24),
                contentDescription = "back icon",
                tint = Light200,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .clickable {
                        navigator.pop()
                    }
            )
        },
        backgroundColor = Teal200,
        elevation = 4.dp
    )
}

@Composable()
fun SavedImages(
    bitmap: Bitmap,
    title: String,
    date: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(true) { }
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier
                    .height(80.dp)
                    .width(60.dp)
                    .padding(
                        start = 8.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = title, fontSize = 16.sp)
                Text(text = date, fontSize = 14.sp)
            }
        }
        Divider(thickness = 1.dp)
    }
}