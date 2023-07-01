package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.accessibility

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.accessibility.AccessibilityRenderExtension
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.compose.parameterized.coffeeDrink
import org.junit.Rule
import org.junit.Test

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:paparazzi:recordPaparazziDebug --tests '*Accessibility*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:paparazzi:verifyPaparazziDebug --tests '*Accessibility*'
 */
class AccessibilityTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            // For Accessibility, better to use devices in landscape with Paparazzi
            deviceConfig = DeviceConfig.NEXUS_5_LAND.copy(softButtons = false),
            renderExtensions = setOf(AccessibilityRenderExtension())
        )

    @Test
    fun snapCoffeeDrinkAppBarComposableWithAccessibility() {
        paparazzi.snapshot(name = "CoffeeDrinkAppBarComposable_Accessibility") {
            AppTheme {
                CoffeeDrinkAppBar()
            }
        }
    }

    @Test
    fun snapCoffeeDrinkListComposableWithAccessibility() {
        paparazzi.snapshot(name = "CoffeeDrinkListComposable_Accessibility") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}
