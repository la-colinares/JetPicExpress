package com.lacolinares.jetpicexpress.presentation.ui.editimage.repository

import android.content.Context
import android.graphics.Bitmap
import com.lacolinares.jetpicexpress.data.imagefilter.ImageFilter
import com.lacolinares.jetpicexpress.presentation.ui.editimage.mapper.EditImageMapper
import jp.co.cyberagent.android.gpuimage.GPUImage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditImageRepositoryImpl @Inject constructor(
    private val context: Context,
    private val mapper: EditImageMapper
) : EditImageRepository {

    override fun loadImageFilters(image: Bitmap): List<ImageFilter> {
        val gpuImage = GPUImage(context).apply {
            setImage(image)
        }

        return mapper.mapToImageFilters(gpuImage)

    }
}