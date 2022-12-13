package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.parameterized

import app.cash.paparazzi.DeviceConfig
import com.android.ide.common.rendering.api.SessionParams
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.paparazzi
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.setPhoneOrientation
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments). Therefore, we need to create 2 different classes to separate @UnhappyPath and
 * @HappyPath tests
 *
 * On the other hand, ParameterizedRunner is compatible with instrumented test of any API level,
 * whereas TestParameterInjector requires API 24+
 */
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
                nightMode = testItem.item.nightMode,
                fontScale = testItem.item.fontScale,
                locale = testItem.item.locale,
            ).setPhoneOrientation(testItem.item.phoneOrientation),
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapComposable() {
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
            nightMode = testItem.item.nightMode,
            fontScale = testItem.item.fontScale,
            locale = testItem.item.locale,
        ).setPhoneOrientation(testItem.item.phoneOrientation),
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
    )

    @Test
    fun snapComposable() {
        paparazzi.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}
