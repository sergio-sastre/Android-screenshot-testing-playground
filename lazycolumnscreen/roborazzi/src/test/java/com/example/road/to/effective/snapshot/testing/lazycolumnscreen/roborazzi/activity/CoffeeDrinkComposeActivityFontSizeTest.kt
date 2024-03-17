package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.activity

import android.os.Build
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksComposeActivity
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.filePath
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.activityscenario.robolectricActivityScenarioForActivityRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A
import sergio.sastre.uitesting.utils.activityscenario.ActivityConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.FontSizeScale
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.common.assumeSdkSupports

/**
 * Execute the command below to run only ActivityTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:roborazzi:recordRoborazziDebug --tests '*FontSize*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:roborazzi:verifyRoborazziDebug --tests '*FontSize*'
 *
 * See results under "Project" View and HTML reports under build/reports/roborazzi/index.html
 */

/**
 * Roborazzi requires Robolectric Native Graphics (RNG) to generate screenshots.
 *
 * Therefore, RNG must be active. In these tests, we do it by annotating tests with @GraphicsMode(NATIVE).
 * Alternatively one could drop the annotation and enable RNG for all Robolectric tests in a module,
 * adding the following in the module's build.gradle:
 *
 *  testOptions {
 *      unitTests {
 *          includeAndroidResources = true
 *          all {
 *              systemProperty 'robolectric.graphicsMode', 'NATIVE'
 *          }
 *      }
 *  }
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
class CoffeeDrinkComposeActivityFontSizeTest(
    private val fontSize: FontSizeScale
) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun fontSizeProvider(): Array<FontSizeScale> =
            arrayOf(
                FontSize.XLARGE,            // same scale but scaling mode is SDK dependent (linear vs. non-linear)
                FontSizeScale.Value(1.75f), // skipped on API < 34 due to usage of assumeSDKSupports
                FontSize.LARGEST            // API 34+ -> 2.0f, API < 34 -> 1.3f
            )
            // or use the method below...
            // FontSizeScale.supportedValuesForCurrentSdk()
    }

    @get:Rule
    val activityScenarioForActivityRule =
        robolectricActivityScenarioForActivityRule<CoffeeDrinksComposeActivity>(
            config = ActivityConfigItem(
                systemLocale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontSize = fontSize,
                displaySize = DisplaySize.NORMAL,
            ),
            deviceScreen = PIXEL_4A,
        )

    @GraphicsMode(NATIVE)
    @Config(sdk = [33, 34])
    @Test
    fun snapActivity() {
        assumeSdkSupports(fontSize)

        activityScenarioForActivityRule
            .rootView
            .captureRoboImage(
                filePath("CoffeeDrinkActivity_FontSize_${fontSize.valueAsName()}_API${Build.VERSION.SDK_INT}")
            )
    }
}