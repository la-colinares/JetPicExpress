package com.lacolinares.jetpicexpress.presentation.ui.editimage.repository

import android.graphics.Bitmap
import com.lacolinares.jetpicexpress.data.imagefilter.ImageFilter

interface EditImageRepository {
    fun loadImageFilters(image: Bitmap) : List<ImageFilter>
}