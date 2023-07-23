package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.compose.parameterized

import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.setContent
import com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule

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
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 *
 * On the other hand, TestParameterInjector requires API 24+ to run with instrumented tests.
 * It throws java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder in lower APIs.
 * Parameterized Runner is compatible with instrumented test of any API level
 */
@SdkSuppress(minSdkVersion = 26) // ScreenshotTest.compareScreenshot(rule = ...) requires API 26+
@RunWith(TestParameterInjector::class)
class CoffeeDrinkListComposableTestParameterHappyPathTest(
    @TestParameter val configItem: HappyPathTestItem,
) : ScreenshotTest {

    @get:Rule
    val activityScenarioForComposableRule = ActivityScenarioForComposableRule(configItem.item)

    @HappyPath
    @ComposableTest
    @Test
    fun snapComposable() {
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }

        compareScreenshot(
            rule = activityScenarioForComposableRule.composeRule,
            name = "CoffeeDrinkListComposable_${configItem.name}_TestParameter"
        )
    }
}

@SdkSuppress(minSdkVersion = 26) // ScreenshotTest.compareScreenshot(rule = ...) requires API 26+
@RunWith(TestParameterInjector::class)
class CoffeeDrinkListComposableTestParameterUnhappyPathTest(
    @TestParameter val configItem: UnhappyPathTestItem,
) : ScreenshotTest {

    @get:Rule
    val activityScenarioForComposableRule = ActivityScenarioForComposableRule(configItem.item)

    @UnhappyPath
    @ComposableTest
    @Test
    fun snapComposable() {
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }

        compareScreenshot(
            rule = activityScenarioForComposableRule.composeRule,
            name = "CoffeeDrinkListComposable_${configItem.name}_TestParameter"
        )
    }
}
