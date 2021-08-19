package com.lacolinares.jetpicexpress.util.extensions

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.lacolinares.jetpicexpress.util.FileHelper
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun Context.getFileUriByName(name: String): Uri {
    val mediaStorageDir = FileHelper.getMediaStorage(this)
    val file = File(mediaStorageDir, name)
    return FileProvider.getUriForFile(this, "${this.packageName}.provider", file)
}

fun Context.getFileLastModified(fileName: String): String {
    val mediaStorageDir = FileHelper.getMediaStorage(this)
    val file = File(mediaStorageDir, fileName)
    val lastModified = file.lastModified()
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(lastModified)
}