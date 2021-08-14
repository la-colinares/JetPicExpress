package com.lacolinares.jetpicexpress.presentation.ui.editimage.mapper

import com.lacolinares.jetpicexpress.data.imagefilter.ImageFilter
import jp.co.cyberagent.android.gpuimage.GPUImage

interface EditImageMapper {
    fun mapToImageFilters(gpuImage: GPUImage): List<ImageFilter>
}