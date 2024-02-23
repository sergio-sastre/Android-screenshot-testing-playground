package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.bitmap

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksFragment
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
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :lazycolumnscreen:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class CoffeeDrinkComposeFragmentToBitmapTest {

    @get:Rule(order = 0)
    val disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    val activityScreenshotRule =
        ScreenshotRuleWithConfigurationForFragment(
            enableReporter = false,
            fragmentClass = CoffeeDrinksFragment::class.java
        )

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapFragmentWithCanvas() {
        activityScreenshotRule
            .configure { this@configure.captureMethod = ::canvasCapture }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .waitForIdleSync()
            .assertSame(
                name = "CoffeeDrinksComposeFragment_WithoutElevation"
            )
    }

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapFragmentWithPixelCopy() {
        activityScreenshotRule
            .configure { this@configure.captureMethod = ::pixelCopyCapture }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .waitForIdleSync()
            .assertSame(
                name = "CoffeeDrinksComposeFragment_WithElevation"
            )
    }
}
