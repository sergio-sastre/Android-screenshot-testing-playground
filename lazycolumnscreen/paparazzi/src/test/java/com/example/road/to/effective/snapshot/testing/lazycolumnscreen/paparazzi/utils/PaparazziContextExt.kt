package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils

import android.content.Context
import android.content.res.Configuration

enum class DisplaySize(val value: Float) {
    SMALL(0.85f),
    NORMAL(1f),
    LARGE(1.1f),
    LARGER(1.2f),
    LARGEST(1.3f);
}

fun Context.setDisplaySize(displaySize: DisplaySize) =
    this.apply {
        val density = resources.configuration.densityDpi
        val config = Configuration(resources.configuration)
        config.densityDpi = (density * displaySize.value).toInt()
        resources.updateConfiguration(config, resources.displayMetrics)
    }
