package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.activity

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
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioConfigurator
import sergio.sastre.uitesting.robolectric.activityscenario.robolectricActivityScenarioForActivityRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A
import sergio.sastre.uitesting.utils.activityscenario.ActivityConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.rootView

/**
 * Execute the command below to run only ActivityTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:roborazzi:recordRoborazziDebug --tests '*Activity*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:roborazzi:verifyRoborazziDebug --tests '*Activity*'
 *
 * See results under "Project" View
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
@RunWith(RobolectricTestRunner::class)
class CoffeeDrinkComposeActivityHappyPathTest {
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
    @Config(sdk = [30])
    @Test
    fun snapActivity() {
        activityScenarioForActivityRule
            .rootView
            .captureRoboImage(
                filePath("CoffeeDrinkActivity_Happy")
            )
    }
}

/**
 *  Example with RobolectricActivityScenarioConfigurator.ForActivity()
 *  of AndroidUiTestingUtils:robolectric
 */
@RunWith(RobolectricTestRunner::class)
class CoffeeDrinkComposeActivityUnhappyPathTest {

    @GraphicsMode(NATIVE)
    @Config(sdk = [30])
    @Test
    fun snapActivity() {
        val activityScenario = RobolectricActivityScenarioConfigurator.ForActivity()
            .setDeviceScreen(PIXEL_4A)
            .setSystemLocale("ar_XB")
            .setUiMode(UiMode.NIGHT)
            .setOrientation(Orientation.LANDSCAPE)
            .setFontSize(FontSize.HUGE)
            .setDisplaySize(DisplaySize.NORMAL)
            .launch(CoffeeDrinksComposeActivity::class.java)

        activityScenario
            .rootView
            .captureRoboImage(
                filePath("CoffeeDrinkActivity_Unhappy")
            )

        activityScenario.close()
    }
}