package com.lacolinares.jetpicexpress.presentation.ui.viewimages.repository

import android.graphics.Bitmap
import java.io.File

interface ViewImagesRepository {
    suspend fun loadSavedImages(): List<Pair<File, Bitmap>>?
    suspend fun deleteImage(fileName: String): Boolean
}