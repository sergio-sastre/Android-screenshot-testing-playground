package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils

import com.github.takahirom.roborazzi.RoborazziOptions
import android.graphics.Bitmap
import android.view.View
import androidx.core.view.drawToBitmap
import sergio.sastre.uitesting.utils.utils.waitForMeasuredView
import java.io.File

fun filePath(name: String): String {
    val path = System.getProperty("user.dir")
    val file = File("$path/src/test", "$name.png")
    return file.path
}

fun View.drawFullScrollableToBitmap(): Bitmap =
    waitForMeasuredView { this }.drawToBitmap()

val roborazziAccessibilityOptions: RoborazziOptions =
    RoborazziOptions(
        captureType = RoborazziOptions.CaptureType.Dump(
            explanation = RoborazziOptions.CaptureType.Dump.AccessibilityExplanation,
        )
    )
