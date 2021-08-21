package com.lacolinares.jetpicexpress.presentation.ui.viewimages.repository

import android.content.Context
import android.graphics.Bitmap
import androidx.core.net.toUri
import com.lacolinares.jetpicexpress.util.FileHelper
import com.lacolinares.jetpicexpress.util.extensions.toBitmap
import java.io.File
import javax.inject.Inject

class ViewImagesRepositoryImpl @Inject constructor(
    val context: Context,
    private val fileHelper: FileHelper,
) : ViewImagesRepository {

    override suspend fun loadSavedImages(): List<Pair<File, Bitmap>>? {
        val savedImages = ArrayList<Pair<File, Bitmap>>()
        val mediaStorageDir = fileHelper.getMediaStorage()
        mediaStorageDir.listFiles()?.let { data ->
            data.forEach { file ->
                val uri = file.toUri()
                val bitmap = uri.toBitmap(context)
                bitmap?.let {
                    savedImages.add(Pair(file, it))
                }
            }
            return savedImages.reversed()
        } ?: return null
    }

    override suspend fun deleteImage(fileName: String): Boolean {
        return fileHelper.deleteFile(fileName)
    }
}