package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.utils

import android.graphics.Color.TRANSPARENT
import androidx.test.platform.app.InstrumentationRegistry.*

import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.BuildConfig
import sergio.sastre.uitesting.android_testify.AndroidTestifyConfig
import sergio.sastre.uitesting.dropshots.DropshotsConfig
import sergio.sastre.uitesting.mapper.paparazzi.PaparazziConfig
import sergio.sastre.uitesting.mapper.paparazzi.wrapper.DeviceConfig
import sergio.sastre.uitesting.mapper.roborazzi.RoborazziConfig
import sergio.sastre.uitesting.mapper.roborazzi.wrapper.CompareOptions
import sergio.sastre.uitesting.mapper.roborazzi.wrapper.ComparisonStyle
import sergio.sastre.uitesting.mapper.roborazzi.wrapper.ImageIoFormat.LosslessWebPImageIoFormat
import sergio.sastre.uitesting.mapper.roborazzi.wrapper.RecordOptions
import sergio.sastre.uitesting.mapper.roborazzi.wrapper.RoborazziOptions
import sergio.sastre.uitesting.mapper.roborazzi.wrapper.screen.DeviceScreen
import sergio.sastre.uitesting.shot.ShotConfig
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
    ): ScreenshotTestRuleForView =
        when (instrumentationScreenshotLibraryName) {
            BuildConfig.SHOT -> shotScreenshotTestRule
            BuildConfig.DROPSHOTS -> dropshotsScreenshotTestRule
            BuildConfig.ANDROID_TESTIFY -> androidTestifyScreenshotTestRule
            // Unfortunately, android-testify does not read instrumentation test runner arguments,
            // so if null, we assume it is android-testify
            else -> androidTestifyScreenshotTestRule
        }

    override fun getJvmScreenshotLibraryTestRule(
        config: ScreenshotConfigForView,
    ): ScreenshotTestRuleForView =
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
    config: ScreenshotConfigForView,
): ScreenshotTestRuleForView =
    CrossLibraryScreenshotTestRule(config)
        // Optional: configure for the libraries you use
        .configure(
            ShotConfig(
                backgroundColor = TRANSPARENT,
                bitmapCaptureMethod = PixelCopy(),
            )
        ).configure(
            DropshotsConfig(
                backgroundColor = TRANSPARENT,
                bitmapCaptureMethod = PixelCopy(),
                resultValidator = ThresholdValidator(0.15f),
            )
        ).configure(
            AndroidTestifyConfig(
                backgroundColor = TRANSPARENT,
                bitmapCaptureMethod = PixelCopy(),
            )
        )
        .configure(
            PaparazziConfig(
                deviceConfig = DeviceConfig.NEXUS_4,
                snapshotViewOffsetMillis = 3_000L,
            )
        ).configure(
            RoborazziConfig(
                deviceScreen = DeviceScreen.Phone.NEXUS_4,
                backgroundColor = TRANSPARENT,
                roborazziOptions = RoborazziOptions(
                    compareOptions = CompareOptions(comparisonStyle = ComparisonStyle.Simple),
                    recordOptions = RecordOptions(
                        // It'd crash if running this test on several sdks set via resources/robolectric.properties.
                        // Don't use this WebP in that case
                        imageIoFormat = LosslessWebPImageIoFormat
                    )
                ),
                filePath = userTestFilePath(),
            )
        )

fun userTestFilePath(): String {
    val path = System.getProperty("user.dir")
    val file = File("$path/src/test/")
    return file.path
}
