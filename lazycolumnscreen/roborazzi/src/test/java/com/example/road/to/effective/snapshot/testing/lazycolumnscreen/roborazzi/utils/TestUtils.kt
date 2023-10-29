package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils

import com.github.takahirom.roborazzi.Dump
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RoborazziOptions
import java.io.File

fun filePath(name: String): String {
    val path = System.getProperty("user.dir")
    val file = File("$path/src/test", "$name.png")
    return file.path
}

@OptIn(ExperimentalRoborazziApi::class)
val roborazziAccessibilityOptions: RoborazziOptions =
    RoborazziOptions(
        captureType = RoborazziOptions.CaptureType.Dump(
            explanation = Dump.Companion.AccessibilityExplanation
        )
    )
