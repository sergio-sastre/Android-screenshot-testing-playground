package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.bitmap

import androidx.compose.ui.platform.ComposeView
import androidx.core.view.drawToBitmap
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.compose.parameterized.coffeeDrink
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.setContent
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule
import sergio.sastre.uitesting.utils.activityscenario.ComposableConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForComposeView

/**
 * Execute the command below to run only BitmapTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.testannotations.BitmapTest -Precord
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.testannotations.BitmapTest
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
 *  NOTE:
 *  Shot's compareScreenshot(composeRule) does not use Canvas, as opposed to other compareScreenshot
 *  methods. That's why it renders elevation of Composables without using drawToBitmapWithElevation(=
 */
class CoffeeDrinkComposableToBitmapTest : ScreenshotTest {

    @get:Rule
    val activityScenarioForComposableRule =
        ActivityScenarioForComposableRule(
            config = ComposableConfigItem(
                locale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            )
        )

    private fun inflateComposable(): ComposeView =
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
            .waitForActivity()
            .waitForComposeView()


    // For API < 26, drawToBitmapWithElevation defaults to Canvas. Thus, draws no elevation
    @BitmapTest
    @Test
    fun snapComposableWithPixelCopy() {
        compareScreenshot(
            bitmap = inflateComposable().drawToBitmapWithElevation(),
            name = "CoffeeDrinkListComposable_BitmapWithElevation"
        )
    }

    @BitmapTest
    @Test
    fun snapComposableWithCanvas() {
        compareScreenshot(
            bitmap = inflateComposable().drawToBitmap(),
            name = "CoffeeDrinkListComposable_BitmapWithoutElevation"
        )
    }
}