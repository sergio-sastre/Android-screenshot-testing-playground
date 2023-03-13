package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtest.parameterized

import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtest.SharedTestRule
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.dropshots.DropshotsConfig
import sergio.sastre.uitesting.shot.ShotConfig
import sergio.sastre.uitesting.utils.BitmapCaptureMethod.PixelCopy
import sergio.sastre.uitesting.utils.ScreenshotConfig
import sergio.sastre.uitesting.sharedtest.paparazzi.PaparazziConfig
import sergio.sastre.uitesting.sharedtest.paparazzi.wrapper.DeviceConfig

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
    val screenshotRule =
        SharedTestRule(
            config = ScreenshotConfig(
                uiMode = testItem.item.uiMode,
                orientation = testItem.item.orientation,
                locale = testItem.item.locale,
                fontScale = testItem.item.fontScale,
            ),
        ).configure(
            ShotConfig(bitmapCaptureMethod = PixelCopy())
        ).configure(
            DropshotsConfig(resultValidator = ThresholdValidator(0.15f))
        ).configure(
            PaparazziConfig(deviceConfig = DeviceConfig.NEXUS_4)
        )

    @Test
    fun snapComposable() {
        screenshotRule.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized") {
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
    val screenshotRule =
        SharedTestRule(
            config = ScreenshotConfig(
                uiMode = testItem.item.uiMode,
                orientation = testItem.item.orientation,
                locale = testItem.item.locale,
                fontScale = testItem.item.fontScale,
            ),
        ).configure(
            ShotConfig(bitmapCaptureMethod = PixelCopy())
        ).configure(
            DropshotsConfig(resultValidator = ThresholdValidator(0.15f))
        ).configure(
            PaparazziConfig(deviceConfig = DeviceConfig.NEXUS_4)
        )

    @Test
    fun snapComposable() {
        screenshotRule.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}
