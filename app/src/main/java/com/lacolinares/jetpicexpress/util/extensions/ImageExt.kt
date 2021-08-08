package com.lacolinares.jetpicexpress.util.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore.Images.Media.getBitmap
import androidx.compose.runtime.mutableStateOf

fun Uri.uriToBitmap(context: Context): Bitmap? {
    val bitmap = mutableStateOf<Bitmap?>(null)

    if (Build.VERSION.SDK_INT < 28) {
        bitmap.value = getBitmap(context.contentResolver,this)

    } else {
        val source = ImageDecoder.createSource(context.contentResolver,this)
        bitmap.value = ImageDecoder.decodeBitmap(source)
    }
    return bitmap.value
}