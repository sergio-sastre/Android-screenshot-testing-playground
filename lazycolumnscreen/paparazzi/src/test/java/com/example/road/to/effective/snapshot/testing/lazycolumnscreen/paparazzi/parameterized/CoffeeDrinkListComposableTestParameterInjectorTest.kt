package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.parameterized

import app.cash.paparazzi.DeviceConfig
import com.android.ide.common.rendering.api.SessionParams
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.paparazzi
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
class CoffeeDrinkListComposableTestParameterInjectorHappyPathTest(
    @TestParameter val testItem: HappyPathTestItem,
) {

    @get:Rule
    val paparazzi =
        paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                screenHeight = 1,
                nightMode = testItem.item.nightMode,
                fontScale = testItem.item.fontScale,
                orientation = testItem.item.screenOrientation,
                locale = testItem.item.locale,
            ),
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    // TODO @HappyPath
    @Test
    fun snapCoffeeDrinkList() {
        paparazzi.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_TestParameter") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}

@RunWith(TestParameterInjector::class)
class CoffeeDrinkListComposableTestParameterInjectorUnhappyPathTest(
    @TestParameter val testItem: UnhappyPathTestItem,
){

    @get:Rule
    val paparazzi =
        paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                screenHeight = 1,
                nightMode = testItem.item.nightMode,
                fontScale = testItem.item.fontScale,
                orientation = testItem.item.screenOrientation,
                locale = testItem.item.locale,
            ),
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    //TODO @UnhappyPath
    @Test
    fun snapCoffeeDrinkList() {
        paparazzi.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_TestParameter") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}
