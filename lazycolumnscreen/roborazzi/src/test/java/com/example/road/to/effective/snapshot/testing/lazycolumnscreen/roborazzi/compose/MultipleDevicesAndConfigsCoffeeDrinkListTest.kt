package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.compose

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkItem
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.R
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.filePath
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForComposableRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Tablet.MEDIUM_TABLET
import sergio.sastre.uitesting.robolectric.utils.composable.TestDataForComposable
import sergio.sastre.uitesting.robolectric.utils.composable.TestDataForComposableCombinator
import sergio.sastre.uitesting.roborazzi.captureRoboImage
import sergio.sastre.uitesting.utils.activityscenario.ComposableConfigItem
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.UiMode.DAY
import sergio.sastre.uitesting.utils.common.UiMode.NIGHT

/**
 * Execute the command below to run only MultipleDevices
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:roborazzi:recordRoborazziDebug --tests '*MultipleDevices*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:roborazzi:verifyRoborazziDebug --tests '*MultipleDevices**'
 *
 * See results under "Project" View and HTML reports under build/reports/roborazzi/index.html
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
class MultipleDevicesAndConfigsCoffeeDrinkListTest(
    private val testItem: TestDataForComposable<CoffeeType>,
) {
    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun testItemProvider(): Array<TestDataForComposable<CoffeeType>> =
            TestDataForComposableCombinator(
                uiStates = CoffeeType.values()
            )
                .forDevices(
                    PIXEL_4A,
                    MEDIUM_TABLET,
                )
                .forConfigs(
                    ComposableConfigItem(uiMode = DAY, locale = "ar_XB"),
                    ComposableConfigItem(uiMode = NIGHT, fontSize = FontSize.HUGE),
                )
                .combineAll()
    }

    @get:Rule
    val activityScenarioForComposableRule =
        RobolectricActivityScenarioForComposableRule(
            config = testItem.config,
            deviceScreen = testItem.device,
        )

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [30])
    @Test
    fun snapComposable() {
        activityScenarioForComposableRule.captureRoboImage(
            filePath("CoffeeDrinkListComposable_${testItem.screenshotId}")
        ) {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = testItem.uiState.drink)
            }
        }
    }
}

enum class CoffeeType(val drink: CoffeeDrinkItem) {
    AMERICANO(
        CoffeeDrinkItem(
            id = 1L,
            name = "Americano",
            imageUrl = R.drawable.americano_small,
            description =
            """
            Americano is a type of coffee drink prepared by diluting an espresso with hot water,
            giving it a similar strength to, but different flavour from, traditionally brewed coffee.
            """
                .trimIndent(),
            ingredients = "Espresso, Water",
            isFavourite = false
        )
    ),
    ESPRESSO(
        CoffeeDrinkItem(
            id = 3L,
            name = "Espresso",
            imageUrl = R.drawable.espresso_small,
            description =
            """
            Espresso is coffee of Italian origin, brewed by forcing a small amount of nearly
            boiling water under pressure (expressing) through finely-ground coffee beans.
            """
                .trimIndent(),
            ingredients = "Ground coffee, Water",
            isFavourite = false
        )
    )
}
