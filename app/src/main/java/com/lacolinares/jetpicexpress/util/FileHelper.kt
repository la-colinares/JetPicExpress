package com.lacolinares.jetpicexpress.util

import android.content.Context
import android.os.Environment
import java.io.File

object FileHelper {

    fun getMediaStorage(context: Context): File {
        return File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Saved Images")
    }
}