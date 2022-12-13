package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi

import app.cash.paparazzi.DeviceConfig
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.PhoneOrientation
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.setPhoneOrientation
import org.junit.Test
import org.junit.Rule

class CoffeeDrinkAppBarHappyPathTest {
    @get:Rule
    val paparazzi =
        paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                nightMode = NightMode.NOTNIGHT,
                fontScale = 1.0f,
                locale = "en",
            ).setPhoneOrientation(PhoneOrientation.PORTRAIT),
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
                nightMode = NightMode.NIGHT,
                fontScale = 1.3f,
                locale = "en",
            ).setPhoneOrientation(PhoneOrientation.LANDSCAPE),
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
