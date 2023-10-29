package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.compose

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.ActionNotSupportedSnackbar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.SnackbarScaffold
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.filePath
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForComposableRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A
import sergio.sastre.uitesting.roborazzi.captureRoboImage

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
class SnackbarComposableTest {

    @get:Rule
    val activityScenarioForComposableRule =
        RobolectricActivityScenarioForComposableRule(PIXEL_4A)

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [30])
    @Test
    fun snapComposable() {
        activityScenarioForComposableRule.captureRoboImage(
            filePath("ActionNotSupportedSnackbar")
        ) {
            AppTheme {
                SnackbarScaffold { snackbarHostState ->
                    ActionNotSupportedSnackbar(
                        snackbarHostState = snackbarHostState,
                        onDismiss = {}
                    )
                }
            }
        }
    }
}
