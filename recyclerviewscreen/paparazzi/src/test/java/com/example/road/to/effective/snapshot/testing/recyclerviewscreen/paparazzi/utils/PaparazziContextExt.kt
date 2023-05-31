package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils

import android.content.res.Configuration
import app.cash.paparazzi.Paparazzi

enum class DisplaySize(val value: Float) {
    SMALL(0.85f),
    NORMAL(1f),
    LARGE(1.1f),
    LARGER(1.2f),
    LARGEST(1.3f);
}

fun Paparazzi.setDisplaySize(displaySize: DisplaySize): Paparazzi =
    this.apply {
        val density = context.resources.configuration.densityDpi
        val config = Configuration(context.resources.configuration)
        config.densityDpi = (density * displaySize.value).toInt()
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
