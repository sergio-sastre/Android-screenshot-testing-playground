package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi

import app.cash.paparazzi.DeviceConfig
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import org.junit.Test
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
/**
 * Example with ActivityScenarioForComposableRule()
 */
class CoffeeDrinkAppBarHappyPathTest {
    @get:Rule
    val paparazzi =
        paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                screenHeight = 1,
                nightMode = NightMode.NOTNIGHT,
                fontScale = 1.0f,
                locale = "en",
            ).setOrientation(PhoneOrientation.PORTRAIT),
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapComposable() {
        paparazzi.snapshot(name = "CoffeeDrinkAppBar_Happy") {
            AppTheme {
                CoffeeDrinkAppBar()
            }
        }
    }
}

class CoffeeDrinkAppBarUnhappyPathTest {
    @get:Rule
    val paparazzi =
        paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                screenHeight = 1,
                nightMode = NightMode.NIGHT,
                fontScale = 1.3f,
                locale = "en",
            ).setOrientation(PhoneOrientation.LANDSCAPE),
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapComposable() {
        paparazzi.snapshot(name = "CoffeeDrinkAppBar_Unhappy") {
            AppTheme {
                CoffeeDrinkAppBar()
            }
        }
    }
}
