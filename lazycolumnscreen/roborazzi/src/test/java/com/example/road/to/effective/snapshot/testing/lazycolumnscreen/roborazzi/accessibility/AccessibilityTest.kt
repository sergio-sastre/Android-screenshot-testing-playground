package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.accessibility

import androidx.compose.ui.test.onRoot
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.compose.parameterized.HappyPathTestItem
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.compose.parameterized.coffeeDrink
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.filePath
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.roborazziAccessibilityOptions
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.setContent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.github.takahirom.roborazzi.captureRoboImage
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForComposableRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:roborazzi:recordRoborazziDebug --tests '*Accessibility*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:roborazzi:verifyRoborazziDebug --tests '*Accessibility*'
 *
 * See results under "Project" View
 */

/**
 * Roborazzi requires Robolectric Native Graphics (RNG) to generate screenshots.
 * Therefore, you can only take Parameterized Screenshot tests with ParameterizedRobolectricTestRunner.
 *
 * Moreover, RNG must be active. In these tests, we do it by annotating tests with @GraphicsMode(NATIVE).
 * Alternatively one could drop the annotation and enable RNG for all Robolectric tests in a module,
 * adding the following in the module's build.gradle:
 *
 *  testOptions {
 *      unitTests {
 *          includeAndroidResources = true
 *          all {
 *              systemProperty 'robolectric.graphicsMode', 'NATIVE' // this
 *          }
 *      }
 *  }
 */
@RunWith(RobolectricTestRunner::class)
class AccessibilityTest {

    @get:Rule
    val activityScenarioForComposableRule = RobolectricActivityScenarioForComposableRule(
        config = HappyPathTestItem.PORTRAIT.item,
        deviceScreen = PIXEL_4A,
    )

    @GraphicsMode(NATIVE)
    @Config(sdk = [30])
    @Test
    fun snapComposableWithAccessibility() {
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }

        }

        activityScenarioForComposableRule.composeRule
            .onRoot()
            .captureRoboImage(
                filePath = filePath("CoffeeDrinkListComposable_Accessibility"),
                roborazziOptions = roborazziAccessibilityOptions,
            )
    }
}
