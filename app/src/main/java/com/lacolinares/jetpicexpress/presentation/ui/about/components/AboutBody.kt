package com.lacolinares.jetpicexpress.presentation.ui.about.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.about.AboutHelper


private val tools = AboutHelper.tools
private val reference = AboutHelper.reference
private val source_code = AboutHelper.source_code
private val developer = AboutHelper.developer

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun AboutBody(
    onClickTool: (String) -> Unit
) {
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Text(text = stringResource(R.string.about_title), fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Text(
            text = stringResource(R.string.app_long_description),
            color = Color.Gray,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = stringResource(R.string.tool_used), fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            cells = GridCells.Fixed(3)
        ) {
            items(tools) { tool ->
                ChipText(text = tool.title) {
                    onClickTool.invoke(tool.url)
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = stringResource(R.string.reference), fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        ChipText(text = reference.title) {
            onClickTool.invoke(reference.url)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = stringResource(R.string.source_code), fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        ChipText(text = source_code.title) {
            onClickTool.invoke(source_code.url)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = stringResource(R.string.developer), fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        ChipText(text = developer.title) {
            onClickTool.invoke(developer.url)
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun ChipText(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
        modifier = Modifier.padding(top = 8.dp, start = 1.dp, end = 1.dp)
    ) {
        Text(text = text, fontSize = 10.sp, color = Color.Gray)
    }
    ChipSpacer()
}

@Composable
private fun ChipSpacer() {
    Spacer(modifier = Modifier.width(6.dp))
}