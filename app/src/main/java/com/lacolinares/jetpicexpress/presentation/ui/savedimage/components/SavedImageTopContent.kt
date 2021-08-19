package com.lacolinares.jetpicexpress.presentation.ui.savedimage.components

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.base.basecomponents.Button
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200


@Composable
fun SavedImageTopContent(
    uri: Uri,
    photo: Bitmap,
    imageName: String,
    dateModified: String,
    modifier: Modifier
){
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SavedPhoto(photo = photo)
            Spacer(modifier = Modifier.width(16.dp))
            PhotoInfo(imageName = imageName, dateModified = dateModified)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Message()
        Spacer(modifier = Modifier.height(24.dp))
        ShareButton(uri = uri)
    }
}

@Composable
private fun ColumnScope.ShareButton(
    uri: Uri
){
    val context = LocalContext.current
    Button(
        text = "Share",
        textColor = Light200,
        buttonBackgroundColor = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.teal_200),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp)
    ) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            type = "image/*"
        }
        context.startActivity(sendIntent)
    }
}

@Composable
private fun ColumnScope.Message(){
    Text(
        text = "Image Saved Successfully!",
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally),
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun RowScope.PhotoInfo(
    imageName: String,
    dateModified: String
){
    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
        Spacer(modifier = Modifier.height(16.dp))
        TitleAndDescription(
            title = "Name:",
            description = imageName
        )
        Spacer(modifier = Modifier.height(16.dp))
        TitleAndDescription(
            title = "Date:",
            description = dateModified
        )
    }
}

@Composable
private fun RowScope.SavedPhoto(
    photo: Bitmap
){
    Image(
        bitmap = photo.asImageBitmap(),
        contentDescription = "saved_image",
        modifier = Modifier
            .height(150.dp)
            .width(100.dp)
            .align(Alignment.CenterVertically),
        contentScale = ContentScale.FillBounds
    )
}


@Composable
private fun TitleAndDescription(
    title: String,
    description: String
) {
    Text(text = title)
    Text(
        text = description,
        fontSize = 16.sp
    )
}