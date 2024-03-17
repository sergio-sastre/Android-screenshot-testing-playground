package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils

import sergio.sastre.uitesting.android_testify.AndroidTestifyConfig
import sergio.sastre.uitesting.mapper.paparazzi.PaparazziConfig
import sergio.sastre.uitesting.mapper.paparazzi.wrapper.DeviceConfig
import sergio.sastre.uitesting.mapper.paparazzi.wrapper.RenderingMode
import sergio.sastre.uitesting.utils.crosslibrary.config.BitmapCaptureMethod.PixelCopy
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForComposable
import sergio.sastre.uitesting.utils.crosslibrary.testrules.ScreenshotTestRuleForComposable
import sergio.sastre.uitesting.utils.crosslibrary.testrules.implementations.shared.SharedScreenshotLibraryTestRuleForComposable

/**
 * A [SharedScreenshotLibraryTestRuleForComposable] that can run the screenshot tests
 * for the JVM and on-device libraries it is configured for.
 */

class CrossLibraryScreenshotTestRule(
    override val config: ScreenshotConfigForComposable,
) : SharedScreenshotLibraryTestRuleForComposable(config) {

    override fun getInstrumentedScreenshotLibraryTestRule(
        config: ScreenshotConfigForComposable,
    ): ScreenshotTestRuleForComposable = androidTestifyScreenshotTestRule

    override fun getJvmScreenshotLibraryTestRule(
        config: ScreenshotConfigForComposable,
    ): ScreenshotTestRuleForComposable = paparazziScreenshotTestRule
}

 fun defaultCrossLibraryScreenshotTestRule(
     config: ScreenshotConfigForComposable,
 ): ScreenshotTestRuleForComposable =
    CrossLibraryScreenshotTestRule(config)
        // Optional: configure for the libraries you use
        // You can override it for each library in every test
        .configure(
            AndroidTestifyConfig(
                bitmapCaptureMethod = PixelCopy(),
            )
        )
        .configure(
            PaparazziConfig(
                deviceConfig = DeviceConfig.NEXUS_4,
                renderingMode = RenderingMode.SHRINK,
            )
        )
