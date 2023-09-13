package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils

import com.dropbox.dropshots.ThresholdValidator
import sergio.sastre.uitesting.dropshots.DropshotsConfig
import sergio.sastre.uitesting.mapper.paparazzi.PaparazziConfig
import sergio.sastre.uitesting.mapper.paparazzi.wrapper.DeviceConfig
import sergio.sastre.uitesting.mapper.paparazzi.wrapper.RenderingMode
import sergio.sastre.uitesting.utils.crosslibrary.config.BitmapCaptureMethod.PixelCopy
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForComposable
import sergio.sastre.uitesting.utils.crosslibrary.testrules.ScreenshotTestRuleForComposable
import sergio.sastre.uitesting.utils.crosslibrary.testrules.implementations.shared.SharedScreenshotLibraryTestRuleForComposable

/**
 * A [SharedScreenshotLibraryTestRuleForComposable] that decides which library runs the instrumented screenshot tests
 * based on the -PscreenshotLibrary argument passed via command line.
 *
 * It required some extra configuration in the gradle file
 * Take a look at the :lazycolumnscreen:dropshots+paparazzi gradle file to see how it is configured
 */

class CrossLibraryScreenshotTestRule(
    override val config: ScreenshotConfigForComposable,
) : SharedScreenshotLibraryTestRuleForComposable(config) {

    override fun getInstrumentedScreenshotLibraryTestRule(
        config: ScreenshotConfigForComposable,
    ): ScreenshotTestRuleForComposable = dropshotsScreenshotTestRule

    override fun getJvmScreenshotLibraryTestRule(
        config: ScreenshotConfigForComposable,
    ): ScreenshotTestRuleForComposable = paparazziScreenshotTestRule

}

 fun defaultCrossLibraryScreenshotTestRule(
     config: ScreenshotConfigForComposable,
 ): ScreenshotTestRuleForComposable =
    CrossLibraryScreenshotTestRule(config)
        // Optional: configure for the libraries you use
        .configure(
            DropshotsConfig(
                bitmapCaptureMethod = PixelCopy(),
                resultValidator = ThresholdValidator(0.15f),
                filePath = "dropshots",
            )
        )
        .configure(
            PaparazziConfig(
                deviceConfig = DeviceConfig.NEXUS_4,
                renderingMode = RenderingMode.SHRINK,
            )
        )
