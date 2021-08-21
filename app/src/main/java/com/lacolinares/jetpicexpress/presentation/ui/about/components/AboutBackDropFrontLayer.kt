package com.lacolinares.jetpicexpress.presentation.ui.about.components

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.lacolinares.jetpicexpress.presentation.ui.about.AboutHelper
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AboutBackDropFrontLayer(
    url: String,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(url) {
            onClose.invoke()
        }
        Body(url)
    }
}

@Composable
private fun Header(
    url: String,
    onClose: () -> Unit
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Light200)) {
        Text(
            text = url,
            modifier = Modifier
                .weight(9f)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .wrapContentWidth(Alignment.Start),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = Color.Gray
        )
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .clickable {
                    onClose.invoke()
                }
        )
    }
}

@Composable
private fun Body(url: String){
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                with(settings) {
                    databaseEnabled = true
                    allowFileAccess = true
                    allowContentAccess = true
                    javaScriptEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                    cacheMode = WebSettings.LOAD_NO_CACHE
                    userAgentString = AboutHelper.USER_AGENT
                }
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        })
    }
}