package com.lacolinares.jetpicexpress.presentation.ui.viewimages

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import com.lacolinares.jetpicexpress.data.viewimages.ViewImagesState
import com.lacolinares.jetpicexpress.presentation.ui.viewimages.repository.ViewImagesRepository
import com.lacolinares.jetpicexpress.util.CoroutineThread
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ViewImagesViewModel @Inject constructor(
    private val viewImagesRepository: ViewImagesRepository
) : ViewModel() {

    private val viewImagesDataState = MutableStateFlow(ViewImagesState())
    val viewImagesUIState: StateFlow<ViewImagesState> get() = viewImagesDataState

    init {
        loadSavedImages()
    }

    fun removeFile(fileName: String) {
        CoroutineThread.io {
            delay(1000)
            kotlin.runCatching {
                viewImagesRepository.deleteImage(fileName)
            }.onSuccess {
                if (it){
                    loadSavedImages()
                }
            }.onFailure {
                Log.e("ViewImagesViewModel", "Error occur while deleting the file...")
            }
        }
    }

    private fun loadSavedImages(){
        CoroutineThread.io {
            kotlin.runCatching {
                emitViewImagesUIState(isLoading = true)
                viewImagesRepository.loadSavedImages()
            }.onSuccess {
                if (it.isNullOrEmpty()){
                    emitViewImagesUIState(error = "No Image Found.")
                }else{
                    emitViewImagesUIState(images = it)
                }
            }.onFailure {
                emitViewImagesUIState(error = it.message.toString())
            }
        }
    }

    private fun emitViewImagesUIState(
        isLoading: Boolean = false,
        images: List<Pair<File, Bitmap>>? = null,
        error: String? = null
    ) {
        val dataState = ViewImagesState(isLoading, images, error)
        viewImagesDataState.value = dataState
    }
}