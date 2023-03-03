package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils

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
        return copy(
            screenWidth = this.screenHeight,
            screenHeight = 1,
            orientation = ScreenOrientation.LANDSCAPE,
        )
    } else {
        this.copy(
            screenHeight = 1,
            orientation = ScreenOrientation.PORTRAIT,
        )
    }
}

private class SetPhoneOrientationException
    : RuntimeException("Do not set DeviceConfig#screenHeight to 1")
