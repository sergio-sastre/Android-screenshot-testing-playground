package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.sharedtests.utils

import android.graphics.Color

import com.dropbox.dropshots.ThresholdValidator
import sergio.sastre.uitesting.dropshots.DropshotsConfig
import sergio.sastre.uitesting.mapper.roborazzi.RoborazziConfig
import sergio.sastre.uitesting.mapper.roborazzi.wrapper.screen.DeviceScreen
import sergio.sastre.uitesting.utils.crosslibrary.config.BitmapCaptureMethod.PixelCopy
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForView
import sergio.sastre.uitesting.utils.crosslibrary.testrules.ScreenshotTestRuleForView
import sergio.sastre.uitesting.utils.crosslibrary.testrules.implementations.shared.SharedScreenshotLibraryTestRuleForView
import java.io.File

/**
 * A [SharedScreenshotLibraryTestRuleForView] that decides which library runs the instrumented screenshot tests
 * based on the -PscreenshotLibrary argument passed via command line.
 *
 * It required some extra configuration in the gradle file
 * Take a look at the :dialogs:crosslibrary gradle file to see how it is configured
 */

class CrossLibraryScreenshotTestRule(
    override val config: ScreenshotConfigForView,
) : SharedScreenshotLibraryTestRuleForView(config) {

    override fun getInstrumentedScreenshotLibraryTestRule(
        config: ScreenshotConfigForView,
    ): ScreenshotTestRuleForView = dropshotsScreenshotTestRule

    override fun getJvmScreenshotLibraryTestRule(
        config: ScreenshotConfigForView,
    ): ScreenshotTestRuleForView = roborazziScreenshotTestRule
}

fun defaultCrossLibraryScreenshotTestRule(
    config: ScreenshotConfigForView,
): ScreenshotTestRuleForView =
    CrossLibraryScreenshotTestRule(config)
        // Optional: configure for the libraries you use
        .configure(
            DropshotsConfig(
                bitmapCaptureMethod = PixelCopy(),
                resultValidator = ThresholdValidator(0.15f),
                filePath = "dropshots",
            )
        ).configure(
            RoborazziConfig(
                deviceScreen = DeviceScreen.Phone.NEXUS_4,
                backgroundColor = Color.TRANSPARENT,
                filePath = userTestFilePath(),
            )
        )

fun userTestFilePath(): String {
    val path = System.getProperty("user.dir")
    val file = File("$path/src/test/")
    return file.path
}
