package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.accessibility

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.compose.parameterized.HappyPathTestItem
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.compose.parameterized.coffeeDrink
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.filePath
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.roborazziAccessibilityOptions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForComposableRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A
import sergio.sastre.uitesting.roborazzi.captureRoboImage

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:roborazzi:recordRoborazziDebug --tests '*Accessibility*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:roborazzi:verifyRoborazziDebug --tests '*Accessibility*'
 *
 * See results under "Project" View and HTML reports under build/reports/roborazzi/index.html
 */

/**
 * Roborazzi requires Robolectric Native Graphics (RNG) to generate screenshots.
 *
 * Therefore, RNG must be active. In these tests, we do it by annotating tests with @GraphicsMode(NATIVE).
 * Alternatively one could drop the annotation and enable RNG for all Robolectric tests in a module,
 * adding the corresponding system property in the module's build.gradle.
 *
 *  testOptions {
 *      unitTests {
 *          includeAndroidResources = true
 *          all {
 *              systemProperty 'robolectric.graphicsMode', 'NATIVE' // this
 *          }
 *      }
 *  }
 *
 *  That's how the experimental Robolectric feature "hardware rendering" is enabled in this module,
 *  which enables rendering of shadows and elevation.
 *  You can delete it in the build.gradle:
 *
 *  systemProperty 'robolectric.pixelCopyRenderMode', 'hardware'
 */
@RunWith(RobolectricTestRunner::class)
class AccessibilityTest {

    @get:Rule
    val activityScenarioForComposableRule = RobolectricActivityScenarioForComposableRule(
        config = HappyPathTestItem.PORTRAIT.configItem,
        deviceScreen = PIXEL_4A,
    )

    @GraphicsMode(NATIVE)
    @Config(sdk = [31])
    @Test
    fun snapCoffeeDrinkAppBarWithAccessibility() {
        activityScenarioForComposableRule.captureRoboImage(
            filePath = filePath("CoffeeDrinkAppBarComposable_Accessibility"),
            roborazziOptions = roborazziAccessibilityOptions,
        ) {
            AppTheme {
                CoffeeDrinkAppBar()
            }

        }
    }

    @GraphicsMode(NATIVE)
    @Config(sdk = [31])
    @Test
    fun snapCoffeeDrinkListWithAccessibility() {
        activityScenarioForComposableRule.captureRoboImage(
            filePath = filePath("CoffeeDrinkListComposable_Accessibility"),
            roborazziOptions = roborazziAccessibilityOptions,
        ) {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}
