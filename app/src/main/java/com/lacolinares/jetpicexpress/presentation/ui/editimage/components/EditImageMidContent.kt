package com.lacolinares.jetpicexpress.presentation.ui.editimage.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.lacolinares.jetpicexpress.presentation.ui.theme.Dark700
import com.lacolinares.jetpicexpress.presentation.ui.theme.Shapes
import com.lacolinares.jetpicexpress.presentation.ui.theme.SkeletonMask
import com.lacolinares.jetpicexpress.presentation.ui.theme.SkeletonShimmer
import com.lacolinares.jetpicexpress.util.CoroutineThread
import kotlinx.coroutines.delay


@Composable
fun EditImageMidContent(
    bitmap: Bitmap,
    modifier: Modifier
) {
    var isVisible by remember { mutableStateOf(true) }
    Box(modifier = modifier.background(Dark700)) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "selected photo",
            modifier = Modifier
                .fillMaxSize()
                .placeholder(
                    visible = isVisible,
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = SkeletonShimmer
                    ),
                    color = SkeletonMask,
                    shape = Shapes.small
                ),
            contentScale = ContentScale.FillBounds
        )
    }
    CoroutineThread.main {
        delay(500)
        isVisible = false
    }
}