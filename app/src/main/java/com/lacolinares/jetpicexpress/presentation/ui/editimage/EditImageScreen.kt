package com.lacolinares.jetpicexpress.presentation.ui.editimage

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.AppNavigator
import com.lacolinares.jetpicexpress.presentation.ui.theme.Dark700
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200
import com.lacolinares.jetpicexpress.presentation.ui.theme.Teal200
import com.lacolinares.jetpicexpress.util.extensions.setTransparentStatusBar
import com.lacolinares.jetpicexpress.util.extensions.uriToBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun EditImageScreen(
    navigator: AppNavigator
) {
    val context = LocalContext.current
    navigator.activity.setTransparentStatusBar(false)

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
        MainContent(bitmap = it)
    }

}

@Composable
fun MainContent(bitmap: Bitmap) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Light200)
    ) {
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

        TopContent(topModifier)
        MidContent(bitmap = bitmap, modifier = midModifier)
        BottomContent(bottomModifier)
    }
}

@Composable
private fun BottomContent(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        LazyRow {
            items(15) { i ->
                Text(text = "Text $i", color = Color.Black)
            }
        }
    }
}

@Composable
private fun MidContent(bitmap: Bitmap, modifier: Modifier) {
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
        modifier = modifier
            .background(color = Teal200),
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