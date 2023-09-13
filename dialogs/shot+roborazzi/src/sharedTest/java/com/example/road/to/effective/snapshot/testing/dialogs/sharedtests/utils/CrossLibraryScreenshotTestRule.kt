package com.example.road.to.effective.snapshot.testing.dialogs.sharedtests.utils

import sergio.sastre.uitesting.mapper.roborazzi.RoborazziConfig
import sergio.sastre.uitesting.mapper.roborazzi.wrapper.screen.DeviceScreen
import sergio.sastre.uitesting.shot.ShotConfig
import sergio.sastre.uitesting.utils.crosslibrary.config.BitmapCaptureMethod.PixelCopy
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForView
import sergio.sastre.uitesting.utils.crosslibrary.testrules.ScreenshotTestRuleForView
import sergio.sastre.uitesting.utils.crosslibrary.testrules.implementations.shared.SharedScreenshotLibraryTestRuleForView
import java.io.File

/**
 * A [SharedScreenshotLibraryTestRuleForView] that runs test using it with Roborazzi on the JVM and
 * with Shot on devices
 */

class CrossLibraryScreenshotTestRule(
    override val config: ScreenshotConfigForView,
) : SharedScreenshotLibraryTestRuleForView(config) {

    override fun getInstrumentedScreenshotLibraryTestRule(
        config: ScreenshotConfigForView,
    ): ScreenshotTestRuleForView = shotScreenshotTestRule

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
            ShotConfig(
                bitmapCaptureMethod = PixelCopy(),
            )
        ).configure(
            RoborazziConfig(
                deviceScreen = DeviceScreen.Phone.NEXUS_4,
                filePath = userTestFilePath(),
            )
        )

fun userTestFilePath(): String {
    val path = System.getProperty("user.dir")
    val file = File("$path/src/test/")
    return file.path
}
