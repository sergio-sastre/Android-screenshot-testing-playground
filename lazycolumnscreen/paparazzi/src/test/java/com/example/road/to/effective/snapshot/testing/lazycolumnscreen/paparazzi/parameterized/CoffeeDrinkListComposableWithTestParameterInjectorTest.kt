package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.parameterized

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.setDisplaySize
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.setPhoneOrientation
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:paparazzi:recordPaparazziDebug --tests '*Composable*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:paparazzi:verifyPaparazziDebug --tests '*Composable*'
 */

/**
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 */
@RunWith(TestParameterInjector::class)
class CoffeeDrinkListComposableTestParameterHappyPathTest(
    @TestParameter val testItem: HappyPathTestItem,
) {

    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                nightMode = testItem.item.nightMode,
                fontScale = testItem.item.fontScale,
                locale = testItem.item.locale,
            ).setPhoneOrientation(testItem.item.phoneOrientation),
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapComposable() {
        paparazzi.context.setDisplaySize(testItem.item.displaySize)

        paparazzi.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_TestParameter") {
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
    val paparazzi =
        Paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                nightMode = testItem.item.nightMode,
                fontScale = testItem.item.fontScale,
                locale = testItem.item.locale,
            ).setPhoneOrientation(testItem.item.phoneOrientation),
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapComposable() {
        paparazzi.context.setDisplaySize(testItem.item.displaySize)

        paparazzi.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_TestParameter") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}
