package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.bitmap

import androidx.core.view.drawToBitmap
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingActivity
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils.filePath
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.activityscenario.robolectricActivityScenarioForActivityRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_5
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation

/**
 * Execute the command below to run only BitmapTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:roborazzi:recordRoborazziDebug --tests '*Bitmap*'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:roborazzi:verifyRoborazziDebug --tests '*FontSize*'
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
class LanguageTrainingActivityToBitmapTest {

    @get:Rule
    val activityScenarioForActivityRule =
        robolectricActivityScenarioForActivityRule<LanguageTrainingActivity>(
            deviceScreen = PIXEL_5,
        )

    @GraphicsMode(NATIVE)
    @Config(sdk = [33])
    @Test
    fun snapActivityWithPixelCopy() {
        activityScenarioForActivityRule
            .rootView
            .drawToBitmapWithElevation()
            .captureRoboImage(
                filePath("LanguageTrainingActivity_BitmapWithElevation")
            )
    }

    @GraphicsMode(NATIVE)
    @Config(sdk = [33])
    @Test
    fun snapActivityWithCanvas() {
        activityScenarioForActivityRule
            .rootView
            .drawToBitmap()
            .captureRoboImage(
                filePath("LanguageTrainingActivity_BitmapWithoutElevation")
            )
    }
}
