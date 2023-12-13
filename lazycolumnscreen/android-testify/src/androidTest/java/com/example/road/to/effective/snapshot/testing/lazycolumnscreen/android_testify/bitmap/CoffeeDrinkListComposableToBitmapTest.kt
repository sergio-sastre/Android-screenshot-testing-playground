package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.bitmap

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.compose.parameterized.coffeeDrink
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import dev.testify.TestifyFeatures.GenerateDiffs
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.processor.capture.canvasCapture
import dev.testify.core.processor.capture.pixelCopyCapture
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.ComposableScreenshotRuleWithConfiguration
import sergio.sastre.uitesting.android_testify.assertSame

/**
 * Execute the command below to run only BitmapTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PrecordModeGmd
 * 2. Verify (move recorded screenshot files first -> https://ndtp.github.io/android-testify/docs/recipes/gmd):
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest
 *
 * WARNING: filtering tests by custom annotation not working with Gradle Managed Devices
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

class CoffeeDrinkListComposableToBitmapTest {

    @get:Rule
    val androidTestifyRule = ComposableScreenshotRuleWithConfiguration(exactness = 0.85f)

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapComposableWithCanvas() {
        androidTestifyRule
            .setCompose {
                AppTheme {
                    CoffeeDrinkList(coffeeDrink = coffeeDrink)
                }
            }
            .configure { this@configure.captureMethod = ::canvasCapture }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(name = "CoffeeDrinkListComposable_BitmapWithoutElevation")
    }

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapComposableWithPixelCopy() {
        androidTestifyRule
            .setCompose {
                AppTheme {
                    CoffeeDrinkList(coffeeDrink = coffeeDrink)
                }
            }
            .configure { this@configure.captureMethod = ::pixelCopyCapture }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(name = "CoffeeDrinkListComposable_BitmapWithElevation")
    }
}