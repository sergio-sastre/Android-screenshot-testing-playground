package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.activity

import android.os.Build
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingActivity
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils.filePath
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.activityscenario.robolectricActivityScenarioForActivityRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_5
import sergio.sastre.uitesting.utils.activityscenario.ActivityConfigItem
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.FontSizeScale
import sergio.sastre.uitesting.utils.common.assumeSdkSupports

/**
 * Execute the command below to run only ActivityTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:roborazzi:recordRoborazziDebug --tests '*FontSize*'
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
 *  You can delete it or set it to false in the build.gradle:
 *
 *  systemProperty 'robolectric.screenshot.hwrdr.native', 'true'
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
class LanguageTrainingActivityFontSizeTest(
    private val fontSize: FontSizeScale
) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun fontSizeProvider(): Array<FontSizeScale> =
            arrayOf(
                FontSize.XLARGE,             // same scale but scaling mode is SDK dependent (linear vs. non-linear)
                FontSizeScale.Value(1.75f),  // skipped on API < 34 due to usage of assumeSDKSupports
                FontSize.LARGEST             // API 34+ -> 2.0f, API < 34 -> 1.3f
            )
        // or use the method below...
        // FontSizeScale.supportedValuesForCurrentSdk()
    }

    @get:Rule
    val activityScenarioForActivityRule =
        robolectricActivityScenarioForActivityRule<LanguageTrainingActivity>(
            config = ActivityConfigItem(
                fontSize = fontSize
            ),
            deviceScreen = PIXEL_5,
        )

    @GraphicsMode(NATIVE)
    @Config(sdk = [33, 34])
    @Test
    fun snapActivity() {
        assumeSdkSupports(fontSize)

        activityScenarioForActivityRule
            .rootView
            .captureRoboImage(
                filePath("LanguageTrainingActivity_FontSize_${fontSize.valueAsName()}_API${Build.VERSION.SDK_INT}")
            )
    }
}
