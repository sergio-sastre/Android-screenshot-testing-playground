package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.bitmap

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.compose.parameterized.coffeeDrink
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import dev.testify.core.processor.capture.canvasCapture
import dev.testify.core.processor.capture.pixelCopyCapture
import dev.testify.scenario.ScreenshotScenarioRule
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.screenshotscenario.assertSame
import sergio.sastre.uitesting.android_testify.screenshotscenario.generateDiffs
import sergio.sastre.uitesting.android_testify.screenshotscenario.setContent
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule

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

class CoffeeDrinkListComposableToBitmapTest {

    @get:Rule(order = 0)
    var composableScenarioRule = ActivityScenarioForComposableRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotScenarioRule(
        configuration = TestifyConfiguration(
            exactness = 0.85f,
            useSoftwareRenderer = true
        ),
    )

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapComposableWithCanvas() {
        screenshotRule
            .withScenario(composableScenarioRule.activityScenario)
            .setScreenshotViewProvider {
                composableScenarioRule
                    .setContent {
                        AppTheme {
                            CoffeeDrinkList(coffeeDrink = coffeeDrink)
                        }
                    }.composeView
            }
            .configure { captureMethod = ::canvasCapture }
            .generateDiffs(true)
            .assertSame(name = "CoffeeDrinkListComposable_BitmapWithoutElevation")
    }

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapComposableWithPixelCopy() {
        screenshotRule
            .withScenario(composableScenarioRule.activityScenario)
            .setScreenshotViewProvider {
                composableScenarioRule
                    .setContent {
                        AppTheme {
                            CoffeeDrinkList(coffeeDrink = coffeeDrink)
                        }
                    }.composeView
            }
            .configure { captureMethod = ::pixelCopyCapture }
            .generateDiffs(true)
            .assertSame(name = "CoffeeDrinkListComposable_BitmapWithElevation")
    }
}