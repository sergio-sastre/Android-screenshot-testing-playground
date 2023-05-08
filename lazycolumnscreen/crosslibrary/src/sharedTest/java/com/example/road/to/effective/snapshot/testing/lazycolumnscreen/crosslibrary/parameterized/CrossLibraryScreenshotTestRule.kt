package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.crosslibrary.parameterized

import androidx.test.platform.app.InstrumentationRegistry.*

import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.crosslibrary.BuildConfig
import sergio.sastre.uitesting.dropshots.DropshotsConfig
import sergio.sastre.uitesting.sharedtest.paparazzi.PaparazziConfig
import sergio.sastre.uitesting.sharedtest.paparazzi.wrapper.DeviceConfig
import sergio.sastre.uitesting.shot.ShotConfig
import sergio.sastre.uitesting.utils.crosslibrary.config.BitmapCaptureMethod
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfig
import sergio.sastre.uitesting.utils.crosslibrary.testrules.ScreenshotTestRule
import sergio.sastre.uitesting.utils.crosslibrary.testrules.SharedScreenshotTestRule

/**
 * A [SharedScreenshotTestRule]that decides which library runs the instrumented screenshot tests
 * based on the -PscreenshotLibrary argument passed via command line.
 */

class CrossLibraryScreenshotTestRule(
    override val config: ScreenshotConfig,
) : SharedScreenshotTestRule(config) {

    override fun getInstrumentedScreenshotTestRule(config: ScreenshotConfig): ScreenshotTestRule =
        when (screenshotLibraryName) {
            BuildConfig.SHOT -> shotScreenshotTestRule
            BuildConfig.DROPSHOTS -> dropshotsScreenshotTestRule
            else -> throw ScreenshotLibraryArgumentMissingException()
        }

    override fun getJvmScreenshotTestRule(config: ScreenshotConfig): ScreenshotTestRule =
        paparazziScreenshotTestRule

    private val screenshotLibraryName
        get() = getArguments().getString(BuildConfig.SCREENSHOT_LIBRARY)

    private class ScreenshotLibraryArgumentMissingException :
        RuntimeException(
            "You must specify a screenshot library when executing instrumented screenshot tests " +
                    "via gradle property. For instance -PscreenshotLibrary=shot"
        )
}

fun defaultCrossLibraryScreenshotTestRule(config: ScreenshotConfig): ScreenshotTestRule =
    CrossLibraryScreenshotTestRule(config).configure(
        ShotConfig(
            bitmapCaptureMethod = BitmapCaptureMethod.PixelCopy(),
        )
    ).configure(
        DropshotsConfig(
            bitmapCaptureMethod = BitmapCaptureMethod.PixelCopy(),
            resultValidator = ThresholdValidator(0.15f),
        )

    ).configure(
        PaparazziConfig(
            deviceConfig = DeviceConfig.NEXUS_4,
        )
    )
