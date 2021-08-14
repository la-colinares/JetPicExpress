package com.lacolinares.jetpicexpress.presentation.ui.home.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lacolinares.jetpicexpress.util.Constants
import com.lacolinares.jetpicexpress.util.SliderHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeImageSlider() {
    val images = SliderHelper.images

    val pagerState = rememberPagerState(
        pageCount = images.size,
        initialOffscreenLimit = 1,
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(Constants.SLIDER_IMAGES_DELAY)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        dragEnabled = false
    ) { page: Int ->
        SliderImage(image = images[page])
    }
}


@Composable
private fun SliderImage(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "slider image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
}