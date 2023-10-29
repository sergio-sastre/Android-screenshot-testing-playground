package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.compose

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onRoot
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
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
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForComposableRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A
import sergio.sastre.uitesting.roborazzi.captureRoboImage
import sergio.sastre.uitesting.utils.activityscenario.ComposableConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:roborazzi:recordRoborazziDebug --tests '*Composable*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:roborazzi:verifyRoborazziDebug --tests '*Composable*'
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
 *              systemProperty 'robolectric.graphicsMode', 'NATIVE' // this
 *          }
 *      }
 *  }
 */
@RunWith(RobolectricTestRunner::class)
class CoffeeDrinkAppBarComposableHappyPathTest {

    @get:Rule
    val activityScenarioForComposableRule =
        RobolectricActivityScenarioForComposableRule(
            config = ComposableConfigItem(
                locale = "en",
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
    fun snapComposable() {
        activityScenarioForComposableRule.captureRoboImage(
            filePath("CoffeeDrinkAppBar_Happy")
        ) {
            AppTheme {
                CoffeeDrinkAppBar()
            }
        }
    }
}

/**
 *  Example with RobolectricActivityScenarioConfigurator.ForComposable()
 *  of AndroidUiTestingUtils:robolectric
 */
@RunWith(RobolectricTestRunner::class)
class CoffeeDrinkAppBarComposableUnhappyPathTest {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @GraphicsMode(NATIVE)
    @Config(sdk = [30])
    @Test
    fun snapComposable() {
        val activityScenario =
            RobolectricActivityScenarioConfigurator.ForComposable()
                .setDeviceScreen(PIXEL_4A)
                .setLocale("en_XA")
                .setUiMode(UiMode.NIGHT)
                .setInitialOrientation(Orientation.LANDSCAPE)
                .setFontSize(FontSize.HUGE)
                .setDisplaySize(DisplaySize.LARGER)
                .launchConfiguredActivity()
                .onActivity {
                    it.setContent {
                        AppTheme {
                            CoffeeDrinkAppBar()
                        }
                    }
                }

        composeTestRule
            .onRoot()
            .captureRoboImage(
                filePath("CoffeeDrinkAppBar_Unhappy")
            )

        activityScenario.close()
    }
}
