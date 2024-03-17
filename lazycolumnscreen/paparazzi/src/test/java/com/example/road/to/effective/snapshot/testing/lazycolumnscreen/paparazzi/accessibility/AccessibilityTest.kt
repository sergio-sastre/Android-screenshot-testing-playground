package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.accessibility

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.accessibility.AccessibilityRenderExtension
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.ScreenOrientation
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.parameterized.coffeeDrink
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
            deviceConfig = DeviceConfig.NEXUS_5.copy(
                orientation = ScreenOrientation.LANDSCAPE
            ),
            renderingMode = SessionParams.RenderingMode.SHRINK,
            renderExtensions = setOf(AccessibilityRenderExtension())
        )

    @Test
    fun snapCoffeeDrinkAppBarWithAccessibility() {
        paparazzi.snapshot(name = "CoffeeDrinkAppBarComposable_Accessibility") {
            AppTheme {
                Box(modifier = Modifier.wrapContentSize()) {
                    CoffeeDrinkAppBar()
                }
            }
        }
    }

    @Test
    fun snapCoffeeDrinkListWithAccessibility() {
        paparazzi.snapshot(name = "CoffeeDrinkListComposable_Accessibility") {
            AppTheme {
                Box(modifier = Modifier.wrapContentSize()) {
                    CoffeeDrinkList(coffeeDrink = coffeeDrink)
                }
            }
        }
    }
}
