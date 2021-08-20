package com.lacolinares.jetpicexpress.presentation.ui.savedimage

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.lacolinares.jetpicexpress.presentation.ui.savedimage.components.SavedImageBottomContent
import com.lacolinares.jetpicexpress.presentation.ui.savedimage.components.SavedImageTopContent
import com.lacolinares.jetpicexpress.util.extensions.getFileLastModified
import com.lacolinares.jetpicexpress.util.extensions.getFileUriByName
import com.lacolinares.jetpicexpress.util.extensions.toBitmap
import com.lacolinares.jetpicexpress.util.extensions.toJpeg
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator

@Composable
fun SavedImageScreen(
    savedImageName: String,
    navigator: AppNavigator
) {
    val context = LocalContext.current
    val imageName = savedImageName.toJpeg()
    val dateModified = context.getFileLastModified(imageName)
    val uri = context.getFileUriByName(imageName)
    val bitmap = uri.toBitmap(context)
    bitmap?.let {
        SavedImageContent(
            uri = uri,
            photo = it,
            imageName = imageName,
            dateModified = dateModified,
            navigator = navigator
        )
    }
}

@Composable
private fun SavedImageContent(
    uri: Uri,
    photo: Bitmap,
    imageName: String,
    dateModified: String,
    navigator: AppNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 48.dp,
                bottom = 48.dp,
                start = 24.dp,
                end = 24.dp
            )
    ) {
        val topContentModifier = Modifier.align(Alignment.TopCenter)
        val bottomContentModifier = Modifier.align(Alignment.BottomCenter)
        SavedImageTopContent(
            uri = uri,
            photo = photo,
            imageName = imageName,
            dateModified = dateModified,
            modifier = topContentModifier
        )
        SavedImageBottomContent(
            modifier = bottomContentModifier,
            navigator = navigator
        )
    }
}