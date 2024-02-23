package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.bitmap

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingFragment
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import dev.testify.TestifyFeatures.GenerateDiffs
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.processor.capture.canvasCapture
import dev.testify.core.processor.capture.pixelCopyCapture
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.ScreenshotRuleWithConfigurationForFragment
import sergio.sastre.uitesting.android_testify.assertSame
import sergio.sastre.uitesting.android_testify.waitForIdleSync
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule

/**
 * Execute the command below to run only BitmapTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :recyclerviewscreen:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example of Tests for Bitmaps to take more realistic screenshots.
 * For that, we draw the Views under tests to bitmaps using PixelCopy & Canvas,each of them
 * obtaining different results:
 *
 * - PixelCopy: draws UI components to bitmap considering elevation. However, use carefully if
 * screenshooting Dialogs/Views/Composables whose size goes beyond the device screen (e.g. ScrollViews).
 * PixelCopy resizes the UI component under test to fit it inside the window. Better use Canvas instead.
 * Moreover, PixelCopy requires API 26+.
 *
 * - Canvas: draws UI components to bitmap without considering elevation. Unlike PixelCopy, it fully
 * screenshots the UI component under tests without resizing it even though it goes beyond the device
 * screen
 */
class LanguageTrainingFragmentToBitmapTest {

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotRuleWithConfigurationForFragment(
        exactness = 0.85f,
        fragmentClass = LanguageTrainingFragment::class.java
    )

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapFragmentWithCanvas() {
        screenshotRule
            .configure { this@configure.captureMethod = ::canvasCapture }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .waitForIdleSync()
            .assertSame(name = "LanguageTrainingFragment_BitmapWithoutElevation")
    }

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapFragmentWithPixelCopy() {
        screenshotRule
            .configure { this@configure.captureMethod = ::pixelCopyCapture }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .waitForIdleSync()
            .assertSame(name = "LanguageTrainingFragment_BitmapWithElevation")
    }
}
