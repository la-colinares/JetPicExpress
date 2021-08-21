package com.lacolinares.jetpicexpress.util

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FileHelper @Inject constructor(private val context: Context) {

    fun getMediaStorage(): File {
        return File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Saved Images")
    }

    fun getFileLastModified(fileName: String): String {
        val mediaStorageDir = getMediaStorage()
        val file = File(mediaStorageDir, fileName)
        val lastModified = file.lastModified()
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(lastModified)
    }

    fun getFileUriByName(name: String): Uri {
        val mediaStorageDir = getMediaStorage()
        val file = File(mediaStorageDir, name)
        return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    }

    fun deleteFile(fileName: String): Boolean{
        val mediaStorageDir = getMediaStorage()
        val file = File(mediaStorageDir, fileName)
        return file.delete()
    }
}