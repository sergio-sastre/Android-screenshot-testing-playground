package com.example.road.to.effective.snapshot.testing.coffeedrinkscompose.shot.compose.parameterized

import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.coffeedrinkscompose.AppTheme
import com.example.road.to.effective.snapshot.testing.coffeedrinkscompose.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.coffeedrinkscompose.shot.utils.setContent
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
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 *
 * On the other hand, TestParameterInjector requires API 24+ to run with instrumented tests.
 * It throws java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder in lower APIs.
 * Parameterized Runner is compatible with instrumented test of any API level
 */
@SdkSuppress(minSdkVersion = 26) // Shot requires API 26+ for testing Composables
@RunWith(TestParameterInjector::class)
class CoffeeDrinkListComposableTestParameterInjectorHappyPathTest(
    @TestParameter val configItem: HappyPathTestItem,
) : ScreenshotTest {

    @get:Rule
    val activityScenarioForComposableRule = ActivityScenarioForComposableRule(configItem.item)

    @HappyPath
    @Test
    fun snapCoffeeDrinkList() {
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }

        compareScreenshot(
            rule = activityScenarioForComposableRule.composeRule,
            name = "CoffeeDrinkListComposable_${configItem.name}_TestParameterInjector"
        )
    }
}

@SdkSuppress(minSdkVersion = 26) // Shot requires API 26+ for testing Composables
@RunWith(TestParameterInjector::class)
class CoffeeDrinkListComposableTestParameterInjectorUnhappyPathTest(
    @TestParameter val configItem: UnhappyPathTestItem,
) : ScreenshotTest {

    @get:Rule
    val activityScenarioForComposableRule = ActivityScenarioForComposableRule(configItem.item)

    @UnhappyPath
    @Test
    fun snapCoffeeDrinkList() {
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }

        compareScreenshot(
            rule = activityScenarioForComposableRule.composeRule,
            name = "CoffeeDrinkListComposable_${configItem.name}_TestParameterInjector"
        )
    }
}
