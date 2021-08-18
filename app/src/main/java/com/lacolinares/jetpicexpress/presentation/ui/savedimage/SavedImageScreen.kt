package com.lacolinares.jetpicexpress.presentation.ui.savedimage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.lacolinares.jetpicexpress.presentation.ui.theme.Teal200
import com.lacolinares.jetpicexpress.util.extensions.getFileUriByName
import com.lacolinares.jetpicexpress.util.extensions.toBitmap
import com.lacolinares.jetpicexpress.util.extensions.toJpeg

@Composable
fun SavedImageScreen(
    imageName: String
){
    val context = LocalContext.current
    val uri = context.getFileUriByName(imageName.toJpeg())
    val bitmap = uri.toBitmap(context)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Teal200)
    ){
        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "saved_photo",
                modifier = Modifier.height(300.dp).fillMaxWidth()
            )
        }
    }
}