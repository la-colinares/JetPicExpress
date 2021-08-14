package com.lacolinares.jetpicexpress.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CoroutineThread {

    fun io(work: suspend (() -> Unit)) {
        CoroutineScope(Dispatchers.IO).launch { work() }
    }

    fun main(work: suspend (() -> Unit)) {
        CoroutineScope(Dispatchers.Main).launch { work() }
    }
}