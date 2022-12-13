package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils

import app.cash.paparazzi.DeviceConfig
import com.android.resources.ScreenOrientation

enum class PhoneOrientation {
    PORTRAIT,
    LANDSCAPE,
}

fun DeviceConfig.setPhoneOrientation(
    phoneOrientation: PhoneOrientation
): DeviceConfig {
    if (screenHeight == 1) {
        throw SetPhoneOrientationException()
    }
    return if (phoneOrientation == PhoneOrientation.LANDSCAPE) {
        this.landscapeOrientation()
    } else {
        this.copy(
            screenHeight = 1,
            orientation = ScreenOrientation.PORTRAIT,
        )
    }
}

private class SetPhoneOrientationException
    : RuntimeException("Do not set DeviceConfig#screenHeight to 1")

fun DeviceConfig.landscapeOrientation(): DeviceConfig {
    val rotatedWidth = screenHeight
    val rotatedHeight = screenWidth
    return copy(
        screenHeight = rotatedHeight,
        screenWidth = rotatedWidth,
        orientation = ScreenOrientation.LANDSCAPE,
    )
}

