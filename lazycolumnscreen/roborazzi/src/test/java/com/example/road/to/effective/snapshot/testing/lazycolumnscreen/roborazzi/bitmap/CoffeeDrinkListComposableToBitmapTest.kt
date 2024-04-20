package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.bitmap

import androidx.compose.ui.platform.ComposeView
import androidx.core.view.drawToBitmap
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.compose.parameterized.coffeeDrink
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.filePath
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForComposableRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A
import sergio.sastre.uitesting.roborazzi.setContent
import sergio.sastre.uitesting.utils.activityscenario.ComposableConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation

/**
 * Execute the command below to run only BitmapTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:roborazzi:recordRoborazziDebug --tests '*Bitmap*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:roborazzi:verifyRoborazziDebug --tests '*Bitmap*'
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
 *  You can delete it or set it to false in the build.gradle:
 *
 *  systemProperty 'robolectric.screenshot.hwrdr.native', 'true'
 */
@RunWith(RobolectricTestRunner::class)
class CoffeeDrinkListComposableToBitmapTest {

    @get:Rule
    val activityScenarioForComposableRule = RobolectricActivityScenarioForComposableRule(
        config = ComposableConfigItem(
            locale = "en",
            uiMode = UiMode.DAY,
            orientation = Orientation.PORTRAIT,
            fontSize = FontSize.NORMAL,
            displaySize = DisplaySize.NORMAL,
        ),
        deviceScreen = PIXEL_4A,
    )

    private fun inflateComposable(): ComposeView =
        activityScenarioForComposableRule
            .setContent {
                AppTheme {
                    CoffeeDrinkList(coffeeDrink = coffeeDrink)
                }
            }.composeView

    @GraphicsMode(NATIVE)
    @Config(sdk = [31])
    @Test
    fun snapComposableWithCanvas() {
        inflateComposable()
            .drawToBitmap()
            .captureRoboImage(
                filePath("CoffeeDrinkListComposable_BitmapWithoutElevation")
            )
    }

    @GraphicsMode(NATIVE)
    @Config(sdk = [31])
    @Test
    fun snapComposableWithPixelCopy() {
        inflateComposable()
            .drawToBitmapWithElevation()
            .captureRoboImage(
                filePath("CoffeeDrinkListComposable_BitmapWithElevation")
            )
    }
}
