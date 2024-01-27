package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.bitmap

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksComposeActivity
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import dev.testify.ScreenshotRule
import dev.testify.TestifyFeatures.GenerateDiffs
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import dev.testify.core.processor.capture.canvasCapture
import dev.testify.core.processor.capture.pixelCopyCapture
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.assertSame
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
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd
 * 2. Verify (move recorded screenshot files first -> https://ndtp.github.io/android-testify/docs/recipes/gmd):
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage
 *
 * WARNING: filtering tests by custom annotation not working with Gradle Managed Devices
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class CoffeeDrinkComposeActivityToBitmapTest {

    @get:Rule(order = 0)
    val disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    val activityScreenshotRule =
        ScreenshotRule(
            configuration = TestifyConfiguration(exactness = 0.85f),
            activityClass = CoffeeDrinksComposeActivity::class.java
        )

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapActivityWithCanvas() {
        activityScreenshotRule
            .configure { this@configure.captureMethod = ::canvasCapture }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(
                name = "CoffeeDrinksComposeActivity_WithoutElevation"
            )
    }

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapActivityWithPixelCopy() {
        activityScreenshotRule
            .configure { this@configure.captureMethod = ::pixelCopyCapture }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(
                name = "CoffeeDrinksComposeActivity_WithElevation"
            )
    }
}
