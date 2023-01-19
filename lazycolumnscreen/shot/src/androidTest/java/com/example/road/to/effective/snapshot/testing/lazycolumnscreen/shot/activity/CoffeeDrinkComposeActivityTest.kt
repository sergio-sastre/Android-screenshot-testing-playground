package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.activity

import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksComposeActivity
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityConfigItem
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.activityscenario.activityScenarioForActivityRule
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.fontsize.FontSizeTestRule
import sergio.sastre.uitesting.utils.testrules.locale.LocaleTestRule
import sergio.sastre.uitesting.utils.testrules.locale.SystemLocaleTestRule
import sergio.sastre.uitesting.utils.testrules.uiMode.UiModeTestRule
import sergio.sastre.uitesting.utils.utils.waitForActivity

/**
 * Example with ActivityScenarioForActivityRule() of AndroidUiTestingUtils
 */
@SdkSuppress(minSdkVersion = 26) // Shot requires API 26+ for testing Composables
class CoffeeDrinkComposeActivityHappyPathTest : ScreenshotTest {

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

    @HappyPath
    @Test
    fun snapActivity() {
        val activity = activityScenarioForActivityRule.activity
        val activityView = activity.window.decorView
        compareScreenshot(
            activity = activity,
            heightInPx = activityView.measuredHeight,
            widthInPx = activityView.measuredWidth,
            name = "CoffeeDrinkComposeActivity_Happy"
        )
    }
}

/**
 * Example with ActivityScenarioConfigurator.ForActivity() of AndroidUiTestingUtils
 *
 * This is an alternative if we cannot use ActivityScenarioForActivityRule(), or we want to use
 * in-app Locale, which ActivityScenarioForActivityRule does not support
 */
@SdkSuppress(minSdkVersion = 26) // Shot requires API 26+ for testing Composables
class CoffeeDrinkComposeActivityUnhappyPathTest : ScreenshotTest {

    // WARNING: in-app Locale prevails over SystemLocale when screenshot testing your app
    @get:Rule
    val appLocale = LocaleTestRule("ar_XB")

    @get:Rule
    val locale = SystemLocaleTestRule("en_XA")

    @get:Rule
    val fontSize = FontSizeTestRule(FontSize.HUGE)

    @get:Rule
    val uiMode = UiModeTestRule(UiMode.NIGHT)

    @UnhappyPath
    @Test
    fun snapActivity() {
        val activityScenario = ActivityScenarioConfigurator.ForActivity()
            .setOrientation(Orientation.LANDSCAPE)
            .launch(CoffeeDrinksComposeActivity::class.java)

        val activity = activityScenario.waitForActivity()
        val activityView = activity.window.decorView

        compareScreenshot(
            activity = activity,
            heightInPx = activityView.measuredHeight,
            widthInPx = activityView.measuredWidth,
            name = "CoffeeDrinkComposeActivity_Unhappy",
        )

        activityScenario.close()
    }
}
