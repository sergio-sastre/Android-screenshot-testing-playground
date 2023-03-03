package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.bitmap

import androidx.core.view.drawToBitmap
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksComposeActivity
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import com.karumi.shot.ScreenshotTest
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
 * Example with ActivityScenarioForActivityRule() of AndroidUiTestingUtils
 */
class CoffeeDrinkComposeActivityToBitmapTest : ScreenshotTest {

    @get:Rule
    val inAppLocale = InAppLocaleTestRule("en")

    @get:Rule
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
    fun snapActivityWithPixelCopy() {
        val activityView = activityScenarioForActivityRule.activity.window.decorView
        compareScreenshot(
            bitmap = activityView.drawToBitmapWithElevation(),
            name = "CoffeeDrinkListComposeActivity_BitmapWithElevation",
        )
    }

    @BitmapTest
    @Test
    fun snapActivityWithCanvas() {
        val activityView = activityScenarioForActivityRule.activity.window.decorView
        compareScreenshot(
            bitmap = activityView.drawToBitmap(),
            name = "CoffeeDrinkListComposeActivity_BitmapWithoutElevation",
        )
    }
}
