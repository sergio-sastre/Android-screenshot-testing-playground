package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.parameterized

import app.cash.paparazzi.DeviceConfig
import com.android.ide.common.rendering.api.SessionParams
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.paparazzi
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CoffeeDrinkAppBarParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

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

    @Test
    fun snapCoffeeDrinkList() {
        paparazzi.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}

@RunWith(Parameterized::class)
class CoffeeDrinkListComposableParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val paparazzi = paparazzi(
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

    @Test
    fun snapCoffeeDrinkList() {
        paparazzi.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}
