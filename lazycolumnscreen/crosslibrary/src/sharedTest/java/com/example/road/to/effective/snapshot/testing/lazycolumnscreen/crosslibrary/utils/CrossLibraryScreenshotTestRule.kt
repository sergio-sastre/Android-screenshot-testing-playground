package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.crosslibrary.utils

import androidx.test.platform.app.InstrumentationRegistry.*

import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.crosslibrary.BuildConfig
import sergio.sastre.uitesting.dropshots.DropshotsConfig
import sergio.sastre.uitesting.sharedtest.paparazzi.PaparazziConfig
import sergio.sastre.uitesting.sharedtest.paparazzi.wrapper.DeviceConfig
import sergio.sastre.uitesting.sharedtest.roborazzi.RoborazziConfig
import sergio.sastre.uitesting.sharedtest.roborazzi.wrapper.screen.DeviceScreen
import sergio.sastre.uitesting.shot.ShotConfig
import sergio.sastre.uitesting.utils.crosslibrary.config.BitmapCaptureMethod.PixelCopy
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfig
import sergio.sastre.uitesting.utils.crosslibrary.testrules.ScreenshotTestRule
import sergio.sastre.uitesting.utils.crosslibrary.testrules.SharedScreenshotTestRule
import java.io.File

/**
 * A [SharedScreenshotTestRule] that decides which library runs the instrumented screenshot tests
 * based on the -PscreenshotLibrary argument passed via command line.
 */

class CrossLibraryScreenshotTestRule(
    override val config: ScreenshotConfig,
) : SharedScreenshotTestRule(config) {

    override fun getInstrumentedScreenshotTestRule(config: ScreenshotConfig): ScreenshotTestRule =
        when (instrumentationScreenshotLibraryName) {
            BuildConfig.SHOT -> shotScreenshotTestRule
            BuildConfig.DROPSHOTS -> dropshotsScreenshotTestRule
            else -> throw ScreenshotLibraryArgumentMissingException()
        }

    override fun getJvmScreenshotTestRule(config: ScreenshotConfig): ScreenshotTestRule =
        when (jvmScreenshotLibraryName) {
            BuildConfig.PAPARAZZI -> paparazziScreenshotTestRule
            BuildConfig.ROBORAZZI -> roborazziScreenshotTestRule
            else -> throw ScreenshotLibraryArgumentMissingException()
        }

    private val instrumentationScreenshotLibraryName
        get() = getArguments().getString(BuildConfig.SCREENSHOT_LIBRARY)

    private val jvmScreenshotLibraryName
        get() = System.getProperty(BuildConfig.SCREENSHOT_LIBRARY)

    private class ScreenshotLibraryArgumentMissingException :
        RuntimeException(
            "You must specify a screenshot library when executing screenshot tests " +
                    "via gradle property. For instance -PscreenshotLibrary=shot"
        )
}

 fun defaultCrossLibraryScreenshotTestRule(
     config: ScreenshotConfig,
 ): ScreenshotTestRule =
    CrossLibraryScreenshotTestRule(config)
        // Optional: configure for the libraries you use
        .configure(
            ShotConfig(
                bitmapCaptureMethod = PixelCopy(),
            )
        ).configure(
            DropshotsConfig(
                bitmapCaptureMethod = PixelCopy(),
                resultValidator = ThresholdValidator(0.15f),
            )
        ).configure(
            PaparazziConfig(
                deviceConfig = DeviceConfig.NEXUS_4,
            )
        ).configure(
            RoborazziConfig(
                filePath = File(userTestFilePath()).path,
                deviceScreen = DeviceScreen.Phone.NEXUS_4,
            )
        )

fun userTestFilePath(): String {
    val path = System.getProperty("user.dir")
    val file = File("$path/src/test/")
    return file.path
}
