package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtest.parameterized

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtest.SharedTestRule
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.ScreenshotConfig

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
    @TestParameter val testItem: HappyPathTestItem,
) {

    @get:Rule
    val screenshotRule =
        SharedTestRule(
            config = ScreenshotConfig(
                uiMode = testItem.item.uiMode,
                orientation = testItem.item.orientation,
                locale = testItem.item.locale,
                fontScale = testItem.item.fontScale,
            )
        )

    @Test
    fun snapComposable() {
        screenshotRule.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_TestParameter") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}

@RunWith(TestParameterInjector::class)
class CoffeeDrinkListComposableTestParameterUnhappyPathTest(
    @TestParameter val testItem: UnhappyPathTestItem,
){

    @get:Rule
    val screenshotRule =
        SharedTestRule(
            config = ScreenshotConfig(
                uiMode = testItem.item.uiMode,
                orientation = testItem.item.orientation,
                locale = testItem.item.locale,
                fontScale = testItem.item.fontScale,
            )
        )

    @Test
    fun snapComposable() {
        screenshotRule.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_TestParameter") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}
