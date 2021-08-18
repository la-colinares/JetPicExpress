package com.lacolinares.jetpicexpress.util.extensions

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(message: String){
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}