package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi

import android.os.Build
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.androidHome
import app.cash.paparazzi.detectEnvironment
import com.android.ide.common.rendering.api.SessionParams
import java.lang.reflect.Field
import java.lang.reflect.Modifier

fun paparazzi(
    deviceConfig: DeviceConfig = DeviceConfig.PIXEL_5,
    renderingMode: SessionParams.RenderingMode = SessionParams.RenderingMode.NORMAL,
): Paparazzi =
    run {
        setFinalStatic(Build.VERSION::class.java.getField("CODENAME"), "REL")
        Paparazzi(
            deviceConfig = deviceConfig,
            renderingMode = renderingMode,
            environment = detectEnvironment().copy(
                platformDir = "${androidHome()}/platforms/android-32",
                compileSdkVersion = 32
            ),
        )
    }

private fun setFinalStatic(field: Field, newValue: Any) {
    Field::class.java.getDeclaredField("modifiers").apply {
        isAccessible = true
        setInt(field, field.modifiers and Modifier.FINAL.inv())
    }

    field.apply {
        isAccessible = true
        set(null, newValue)
    }
}
