package com.lacolinares.jetpicexpress.util.extensions

import android.app.Activity
import android.graphics.Color
import androidx.core.view.WindowCompat

fun Activity.setTransparentStatusBar(transparent: Boolean = true) {
    if (transparent) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
    } else {
        WindowCompat.setDecorFitsSystemWindows(window, true)
    }
}