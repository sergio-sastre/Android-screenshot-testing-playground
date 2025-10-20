package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.bitmap

import androidx.core.view.drawToBitmap
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksComposeActivity
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityConfigItem
import sergio.sastre.uitesting.utils.activityscenario.activityScenarioForActivityRule
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.locale.InAppLocaleTestRule
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation

/**
 * Execute the command below to run only BitmapTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:dropshots:recordScreenshots -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
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
 * drawToBitmapWithElevation() uses PixelCopy but defaults to Canvas (i.e. no elevation) in lower APIs.
 *
 * - Canvas: draws UI components to bitmap without considering elevation. Unlike PixelCopy, it fully
 * screenshots the UI component under tests without resizing it even though it goes beyond the device
 * screen
 *
 */
class CoffeeDrinkComposeActivityToBitmapToTest {

    @get:Rule(1)
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

    // WARNING: in-app Locale prevails over SystemLocale when screenshot testing your app
    @get:Rule(2)
    val inAppLocale = InAppLocaleTestRule("en")

    @get:Rule(3)
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<CoffeeDrinksComposeActivity>(
            config = ActivityConfigItem(
                systemLocale = "en",
                orientation = Orientation.PORTRAIT,
                uiMode = UiMode.DAY,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            )
        )

    // For API < 26, drawToBitmapWithElevation defaults to Canvas. Thus, draws no elevation
    @BitmapTest
    @Test
    fun snapComposableWithPixelCopy() {
        val activityView = activityScenarioForActivityRule.activity.window.decorView
        dropshots.assertSnapshot(
            bitmap = activityView.drawToBitmapWithElevation(),
            name = "CoffeeDrinkComposeActivity_BitmapWithElevation",
            filePath = "bitmap",
        )
    }

    @BitmapTest
    @Test
    fun snapComposableWithCanvas() {
        val activityView = activityScenarioForActivityRule.activity.window.decorView
        dropshots.assertSnapshot(
            bitmap = activityView.drawToBitmap(),
            name = "CoffeeDrinkComposeActivity_BitmapWithoutElevation",
            filePath = "bitmap",
        )
    }
}
