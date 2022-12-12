package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi

import app.cash.paparazzi.DeviceConfig
import com.android.resources.ScreenOrientation

enum class PhoneOrientation {
    PORTRAIT,
    LANDSCAPE,
}

fun DeviceConfig.setOrientation(
    screenOrientation: PhoneOrientation
): DeviceConfig =
    if (screenOrientation == PhoneOrientation.LANDSCAPE) {
        this.landscapeOrientation()
    } else {
        this.copy(screenHeight = 1)
    }

fun DeviceConfig.landscapeOrientation(): DeviceConfig {
    val rotatedWidth = screenHeight
    val rotatedHeight = screenWidth
    return copy(
        screenHeight = rotatedHeight,
        screenWidth = rotatedWidth,
        orientation = ScreenOrientation.LANDSCAPE,
    )
}
