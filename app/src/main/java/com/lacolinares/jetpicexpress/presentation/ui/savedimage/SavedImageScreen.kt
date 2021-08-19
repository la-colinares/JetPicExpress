package com.lacolinares.jetpicexpress.presentation.ui.savedimage

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.theme.Dark200
import com.lacolinares.jetpicexpress.presentation.ui.theme.Light200
import com.lacolinares.jetpicexpress.util.extensions.getFileLastModified
import com.lacolinares.jetpicexpress.util.extensions.getFileUriByName
import com.lacolinares.jetpicexpress.util.extensions.toBitmap
import com.lacolinares.jetpicexpress.util.extensions.toJpeg
import com.lacolinares.jetpicexpress.util.navigation.AppNavigator

@Composable
fun SavedImageScreen(
    savedImageName: String,
    navigator: AppNavigator
) {
    val context = LocalContext.current
    val imageName = savedImageName.toJpeg()
    val dateModified = context.getFileLastModified(imageName)
    val uri = context.getFileUriByName(imageName)
    val bitmap = uri.toBitmap(context)
    bitmap?.let {
        SavedImageContent(
            photo = it,
            imageName = imageName,
            dateModified = dateModified,
            navigator = navigator
        )
    }
}

@Composable
private fun SavedImageContent(
    photo: Bitmap,
    imageName: String,
    dateModified: String,
    navigator: AppNavigator
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 48.dp,
                bottom = 48.dp,
                start = 24.dp,
                end = 24.dp
            )
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    bitmap = photo.asImageBitmap(),
                    contentDescription = "saved_image",
                    modifier = Modifier
                        .height(150.dp)
                        .width(100.dp)
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(16.dp))
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
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Image Saved Successfully!",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
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
                    putExtra(Intent.EXTRA_STREAM, Uri.parse(""))
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    type = "image/*"
                }
                context.startActivity(sendIntent)
            }
        }
        Button(
            text = "Back to Home",
            buttonBackgroundColor = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.white),
            ),
            modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.BottomCenter)
        ) {
            navigator.pop()
        }
    }
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

@Composable
private fun Button(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Dark200,
    buttonBackgroundColor: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorResource(id = R.color.white)
    ),
    fontSize: Int = 14,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(50),
        modifier = modifier,
        colors = buttonBackgroundColor
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = fontSize.sp,
            modifier = Modifier.padding(4.dp)
        )
    }
}