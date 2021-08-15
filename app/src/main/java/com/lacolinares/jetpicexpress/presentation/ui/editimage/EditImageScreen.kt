package com.lacolinares.jetpicexpress.presentation.ui.editimage

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.theme.Dark700
import com.lacolinares.jetpicexpress.presentation.ui.theme.DarkTransparent
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200
import com.lacolinares.jetpicexpress.presentation.ui.theme.Teal200
import com.lacolinares.jetpicexpress.util.extensions.setTransparentStatusBar
import com.lacolinares.jetpicexpress.util.extensions.uriToBitmap
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator
import jp.co.cyberagent.android.gpuimage.GPUImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun EditImageScreen(
    viewModel: EditImageViewModel,
    navigator: AppNavigator
) {
    val context = LocalContext.current
    navigator.activity.setTransparentStatusBar(false)

    val gpuImage = GPUImage(context)

    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            val bitmap = uri.uriToBitmap(context = context)
            bitmap?.let {
                imageBitmap = it
            }
        } else {
            navigator.pop()
        }
    }
    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.IO) {
            imagePicker.launch("image/*")
        }
    }

    imageBitmap?.let {
        viewModel.setFilteredBitmap(it)
        MainContent(
            originalBitmap = it,
            gpuImage = gpuImage,
            viewModel = viewModel
        )
    }
}

@Composable
fun MainContent(
    originalBitmap: Bitmap,
    gpuImage: GPUImage,
    viewModel: EditImageViewModel
) {
    gpuImage.setImage(originalBitmap)
    viewModel.loadImageFilters(originalBitmap)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Light200)
    ) {
        //region:: Constraint Setup
        val (topContent, midContent, bottomContent) = createRefs()

        val topModifier = Modifier.constrainAs(topContent) {
            width = Dimension.fillToConstraints
            height = Dimension.value(56.dp)
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        val midModifier = Modifier.constrainAs(midContent) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(topContent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(bottomContent.top)
        }

        val bottomModifier = Modifier.constrainAs(bottomContent) {
            width = Dimension.fillToConstraints
            height = Dimension.value(120.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        //endregion

        TopContent(topModifier)
        val filteredBitmap = viewModel.filteredBitmap.collectAsState().value
        filteredBitmap?.let{
            MidContent(
                bitmap = it,
                modifier = midModifier
            )
        }
        BottomContent(
            viewModel = viewModel,
            gpuImage = gpuImage,
            modifier = bottomModifier
        )
    }
}

@Composable
private fun BottomContent(
    viewModel: EditImageViewModel,
    gpuImage: GPUImage,
    modifier: Modifier
) {
    val imageFilters = viewModel
        .imageFilterUIState
        .collectAsState()
        .value
        .imageFilters

    val listState = rememberLazyListState()
    Box(
        modifier = modifier
    ) {
        LazyRow(state = listState) {
            items(imageFilters) { imageFilter ->
                ImageFilter(
                    image = imageFilter.filterPreview,
                    filterName = imageFilter.name
                ) {
                    with(imageFilter) {
                        gpuImage.setFilter(filter)
                        viewModel.setFilteredBitmap(gpuImage.bitmapWithFilterApplied)
                    }
                }
            }
        }
    }
}

@Composable
private fun ImageFilter(
    image: Bitmap,
    filterName: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(90.dp)
            .background(Light200)
            .padding(6.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(enabled = true) {
                onClick.invoke()
            }
    ) {
        Image(
            bitmap = image.asImageBitmap(),
            contentDescription = "filter image",
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(),
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = filterName,
            color = Light200,
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkTransparent)
                .align(Alignment.BottomCenter),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
    }
}

@Preview(name = "Filter")
@Composable()
fun ImageFilterPreview() {
    Box(
        modifier = Modifier
            .width(90.dp)
            .background(Light200)
            .padding(6.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.slider_three),
            contentDescription = "filter image",
            modifier = Modifier
                .height(120.dp)
                .width(90.dp)
            ,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "Test",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkTransparent)
                .align(Alignment.BottomCenter),
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )
    }
}

@Composable
private fun MidContent(
    bitmap: Bitmap,
    modifier: Modifier
) {
    Box(modifier = modifier.background(Dark700)) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "selected photo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
private fun TopContent(modifier: Modifier) {
    Row(
        modifier = modifier.background(color = Teal200),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back_24),
            contentDescription = "back icon",
            tint = Light200,
            modifier = Modifier.padding(start = 12.dp)
        )
        Text(
            text = "Apply Filter",
            color = Light200,
            fontSize = 18.sp
        )
    }
}