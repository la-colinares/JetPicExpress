package com.lacolinares.jetpicexpress.presentation.ui.about

import com.lacolinares.jetpicexpress.data.about.AboutToolsAndTech

object AboutHelper {

    const val USER_AGENT = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19"

    val tools = listOf(
        AboutToolsAndTech("Android Studio", "https://developer.android.com/studio"),
        AboutToolsAndTech("Kotlin for Android", "https://kotlinlang.org/docs/android-overview.html"),
        AboutToolsAndTech("Jetpack Compose", "https://developer.android.com/jetpack/compose"),
        AboutToolsAndTech("Dagger-Hilt", "https://developer.android.com/training/dependency-injection/hilt-android"),
        AboutToolsAndTech("ViewModel", "https://developer.android.com/topic/libraries/architecture/viewmodel"),
        AboutToolsAndTech("GPUImage", "https://github.com/cats-oss/android-gpuimage")
    )

    val reference = AboutToolsAndTech("Android Image Filters App", "https://www.youtube.com/watch?v=dtlZENmOzp4&list=PLam6bY5NszYOGk7-8S9F3K4YpjLt2nKv8&index=1")
    val source_code = AboutToolsAndTech("JetPic Express (Github)", "https://github.com/la-colinares/JetPicExpress")
    val developer = AboutToolsAndTech("Lowi Adrian G. Colinares", "https://github.com/la-colinares")
}