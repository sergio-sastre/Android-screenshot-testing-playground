package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.bitmap

import androidx.core.view.drawToBitmap
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksComposeActivity
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.filePath
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.activityscenario.robolectricActivityScenarioForActivityRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A
import sergio.sastre.uitesting.utils.activityscenario.ActivityConfigItem
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
 *  You can delete it in the build.gradle:
 *
 *  systemProperty 'robolectric.pixelCopyRenderMode', 'hardware'
 */
@RunWith(RobolectricTestRunner::class)
class CoffeeDrinkComposeActivityToBitmapTest {
    @get:Rule
    val activityScenarioForActivityRule =
        robolectricActivityScenarioForActivityRule<CoffeeDrinksComposeActivity>(
            config = ActivityConfigItem(
                systemLocale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            ),
            deviceScreen = PIXEL_4A,
        )

    @GraphicsMode(NATIVE)
    @Config(sdk = [31])
    @Test
    fun snapActivityWithCanvas() {
        activityScenarioForActivityRule
            .rootView
            .drawToBitmap()
            .captureRoboImage(
                filePath("CoffeeDrinkActivity_BitmapWithoutElevation")
            )
    }

    @GraphicsMode(NATIVE)
    @Config(sdk = [31])
    @Test
    fun snapActivityWithPixelCopy() {
        activityScenarioForActivityRule
            .rootView
            .drawToBitmapWithElevation()
            .captureRoboImage(
                filePath("CoffeeDrinkActivity_BitmapWithElevation")
            )
    }
}
