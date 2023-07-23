package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.compose

import android.os.Build
import androidx.compose.ui.test.onRoot
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.filePath
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.roborazzi.utils.setContent
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForComposableRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen
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
 * See results under "Project" View
 */

/**
 *  Example of Robolectric Screenshot test for different API levels with Roborazzi
 *  This is possible due to Robolectric's annotation @Config(sdk = [30, 31])
 */
@RunWith(RobolectricTestRunner::class)
class DifferentApiLevelsCoffeeDrinkAppBarComposableTest {

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
            deviceScreen = DeviceScreen.Phone.PIXEL_4A,
        )

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [30, 31]) // Configure API levels here
    @Test
    fun snapComposableInDifferentApiLevels() {
        val sdkVersion = Build.VERSION.SDK_INT
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkAppBar(coffeeShopName = "API $sdkVersion")
            }
        }

        activityScenarioForComposableRule.composeRule
            .onRoot()
            .captureRoboImage(
                filePath("CoffeeDrinkAppBar_API_$sdkVersion")
            )
    }
}
