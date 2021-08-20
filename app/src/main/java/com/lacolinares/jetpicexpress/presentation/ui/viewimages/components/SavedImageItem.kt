package com.lacolinares.jetpicexpress.presentation.ui.viewimages.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.lacolinares.jetpicexpress.presentation.ui.theme.SkeletonMask
import com.lacolinares.jetpicexpress.presentation.ui.theme.SkeletonShimmer
import com.lacolinares.jetpicexpress.util.CoroutineThread
import kotlinx.coroutines.delay

@Composable()
fun SavedImageItem(
    bitmap: Bitmap,
    title: String,
    date: String
) {
    var isVisible by remember { mutableStateOf(title.isNotEmpty()) }

    val skeleton = Modifier.placeholder(
        visible = isVisible,
        highlight = PlaceholderHighlight.shimmer(
            highlightColor = SkeletonShimmer
        ),
        color = SkeletonMask
    )

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(true) { }
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "",
                modifier = skeleton
                    .height(80.dp)
                    .width(60.dp)
                    .padding(
                        start = 8.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    modifier = skeleton
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = date,
                    fontSize = 14.sp,
                    modifier = skeleton
                )
            }
        }
        Divider(thickness = 1.dp)
    }
    CoroutineThread.main {
        delay(500)
        isVisible = false
    }
}