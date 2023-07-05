package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.compose

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.utils.setContent
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.utils.DropshotsAPI29Fix
import com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.Rule
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
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest -Pdropshots.record
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 */

/**
 * Example with ActivityScenarioForComposableRule() of AndroidUiTestingUtils
 */
class CoffeeDrinkAppBarHappyPathTest {

    @get:Rule
    val dropshots = DropshotsAPI29Fix(
        Dropshots(resultValidator = ThresholdValidator(0.15f))
    )

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

        dropshots.assertSnapshot(
            view = activityScenarioForComposableRule.composeView,
            name = "CoffeeDrinkAppBar_Happy",
        )
    }
}

/**
 * Example with ActivityScenarioConfigurator.ForComposable() of AndroidUiTestingUtils
 * This is an alternative if we cannot use ActivityScenarioForComposableRule()
 */
class CoffeeDrinkAppBarUnhappyPathTest {

    @get:Rule
    val dropshots = DropshotsAPI29Fix(
        Dropshots(resultValidator = ThresholdValidator(0.15f))
    )

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
                .setFontSize(FontSize.HUGE)
                .setDisplaySize(DisplaySize.LARGER)
                .launchConfiguredActivity()
                .onActivity {
                    it.setContent {
                        AppTheme {
                            CoffeeDrinkAppBar()
                        }
                    }
                }

        dropshots.assertSnapshot(
            view = activityScenario.waitForActivity().waitForComposeView(),
            name = "CoffeeDrinkAppBar_Unhappy",
        )

        activityScenario.close()
    }
}
