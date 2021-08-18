package com.lacolinares.jetpicexpress.presentation.ui.editimage.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.lacolinares.jetpicexpress.presentation.ui.theme.Dark700


@Composable
fun EditImageMidContent(
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