package com.lacolinares.jetpicexpress.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.lacolinares.jetpicexpress.presentation.ui.theme.JetPicExpressTheme
import com.lacolinares.jetpicexpress.util.extensions.setTransparentStatusBar
import com.lacolinares.jetpicexpress.util.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()
        setContent {
            JetPicExpressTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Navigation(this)
                }
            }
        }
    }
}