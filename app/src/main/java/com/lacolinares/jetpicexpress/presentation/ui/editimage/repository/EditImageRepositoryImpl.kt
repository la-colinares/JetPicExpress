package com.lacolinares.jetpicexpress.presentation.ui.editimage.repository

import android.content.Context
import android.graphics.Bitmap
import com.lacolinares.jetpicexpress.data.imagefilter.ImageFilter
import com.lacolinares.jetpicexpress.presentation.ui.editimage.mapper.EditImageMapper
import com.lacolinares.jetpicexpress.util.FileHelper
import jp.co.cyberagent.android.gpuimage.GPUImage
import java.io.File
import java.io.FileOutputStream
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

    override suspend fun saveFilteredImage(filteredBitmap: Bitmap): String? {
        return try {
            val mediaStorageDir = FileHelper.getMediaStorage(context)
            if (!mediaStorageDir.exists()){
                mediaStorageDir.mkdirs()
            }
            val fileExt = ".jpg"
            val fileName = "JE_IMG_${System.currentTimeMillis()}$fileExt"
            val file = File(mediaStorageDir, fileName)
            saveFile(file = file, bitmap = filteredBitmap)
            fileName.removeSuffix(fileExt)
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    private fun saveFile(file: File, bitmap: Bitmap){
        with(FileOutputStream(file)){
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, this)
            flush()
            close()
        }
    }
}