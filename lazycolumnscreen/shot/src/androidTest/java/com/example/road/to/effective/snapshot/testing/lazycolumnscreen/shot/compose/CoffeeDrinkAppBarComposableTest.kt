package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.compose

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.utils.setContent
import com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule
import sergio.sastre.uitesting.utils.activityscenario.ComposableConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.waitForActivity

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest -Precord
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example with ActivityScenarioForComposableRule() of AndroidUiTestingUtils
 */
@SdkSuppress(minSdkVersion = 26) // ScreenshotTest.compareScreenshot(rule = ...) requires API 26+
class CoffeeDrinkAppBarHappyPathTest : ScreenshotTest {

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

    @HappyPath
    @ComposableTest
    @Test
    fun snapComposable() {
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkAppBar()
            }
        }

        compareScreenshot(
            rule = activityScenarioForComposableRule.composeRule,
            name = "CoffeeDrinkAppBar_Happy",
        )
    }
}

/**
 * Example with ActivityScenarioConfigurator.ForComposable() of AndroidUiTestingUtils
 * This is an alternative if we cannot use ActivityScenarioForComposableRule()
 */
@SdkSuppress(minSdkVersion = 26) // ScreenshotTest.compareScreenshot(rule = ...) requires API 26+
class CoffeeDrinkAppBarUnhappyPathTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @UnhappyPath
    @ComposableTest
    @Test
    fun snapComposable() {
        val activityScenario =
            ActivityScenarioConfigurator.ForComposable()
                .setLocale("ar_XB")
                .setUiMode(UiMode.NIGHT)
                .setInitialOrientation(Orientation.LANDSCAPE)
                .setFontSize(FontSize.LARGEST)
                .setDisplaySize(DisplaySize.LARGER)
                .launchConfiguredActivity()
                .onActivity {
                    it.setContent {
                        AppTheme {
                            CoffeeDrinkAppBar()
                        }
                    }
                }

        activityScenario.waitForActivity()

        compareScreenshot(
            rule = composeTestRule,
            name = "CoffeeDrinkAppBar_Unhappy",
        )

        activityScenario.close()
    }
}
