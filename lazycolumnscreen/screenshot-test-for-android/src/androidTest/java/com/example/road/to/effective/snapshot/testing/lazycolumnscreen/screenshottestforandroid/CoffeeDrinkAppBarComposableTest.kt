package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.screenshottestforandroid

import android.app.Activity
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.Rule
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.core.app.ActivityScenario
import com.facebook.testing.screenshot.Screenshot
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule
import sergio.sastre.uitesting.utils.activityscenario.ComposableConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForComposeView

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:dropshots:recordScreenshots -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

fun ActivityScenarioForComposableRule.setContent(
    content: @Composable () -> Unit
): ActivityScenario<out Activity> =
    activityScenario.onActivity {
        it.setContent { content() }
    }

/**
 * Example with ActivityScenarioForComposableRule() of AndroidUiTestingUtils
 */
class CoffeeDrinkAppBarHappyPathTest {

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

        Screenshot
            .snap(activityScenarioForComposableRule.composeView)
            .setName("CoffeeDrinkAppBar_Happy")
            .record()
    }
}

/**
 * Example with ActivityScenarioConfigurator.ForComposable() of AndroidUiTestingUtils
 * This is an alternative if we cannot use ActivityScenarioForComposableRule()
 */
class CoffeeDrinkAppBarUnhappyPathTest {

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

        Screenshot
            .snap(activityScenario.waitForActivity().waitForComposeView())
            .setName("CoffeeDrinkAppBar_Unhappy")
            .record()

        activityScenario.close()
    }
}