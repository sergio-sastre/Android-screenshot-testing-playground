package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.compose.parameterized

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.utils.setContent
import com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule
import sergio.sastre.uitesting.utils.utils.waitForComposeView

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest -Pdropshots.record
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
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
@RunWith(TestParameterInjector::class)
class CoffeeDrinkListComposableTestParameterHappyPathTest(
    @TestParameter val configItem: HappyPathTestItem,
) {

    @get:Rule
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

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

        dropshots.assertSnapshot(
            view = activityScenarioForComposableRule.activity.waitForComposeView(),
            name = "CoffeeDrinkListComposable_${configItem.name}_TestParameter",
            filePath = "compose",
        )
    }
}

@RunWith(TestParameterInjector::class)
class CoffeeDrinkListComposableTestParameterInjectorUnhappyPathTest(
    @TestParameter val configItem: UnhappyPathTestItem,
){

    @get:Rule
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

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

        dropshots.assertSnapshot(
            view = activityScenarioForComposableRule.activity.waitForComposeView(),
            name = "CoffeeDrinkListComposable_${configItem.name}_TestParameter",
            filePath = "compose",
        )
    }
}

