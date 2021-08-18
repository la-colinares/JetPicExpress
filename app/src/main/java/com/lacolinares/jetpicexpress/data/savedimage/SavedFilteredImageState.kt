package com.lacolinares.jetpicexpress.data.savedimage

data class SavedFilteredImageState(
    val isLoading: Boolean = false,
    val imageName: String? = null,
    val error: String? = null
)