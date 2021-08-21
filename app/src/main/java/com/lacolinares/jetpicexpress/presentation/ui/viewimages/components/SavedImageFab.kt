package com.lacolinares.jetpicexpress.presentation.ui.viewimages.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lacolinares.jetpicexpress.R
import com.lacolinares.jetpicexpress.presentation.ui.theme.Teal200
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun SavedImageFab(
    modifier: Modifier,
    listState: LazyListState,
    coroutineScope: CoroutineScope
) {
    AnimatedVisibility(
        visible = true,
        modifier = modifier
    ) {
        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(index = 0)
                }
            },
            shape = RoundedCornerShape(50),
            backgroundColor = Teal200,
            modifier = modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_up_24),
                contentDescription = "top_icon",
                tint = Color.White
            )
        }
    }
}