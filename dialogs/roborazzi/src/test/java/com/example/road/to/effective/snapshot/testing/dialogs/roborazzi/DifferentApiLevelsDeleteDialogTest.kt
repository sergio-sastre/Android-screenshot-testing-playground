package com.example.road.to.effective.snapshot.testing.dialogs.roborazzi

import android.graphics.Color.TRANSPARENT
import android.os.Build
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForViewRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.drawToBitmap
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

/**
 * Execute the command below to run only DialogTests
 * 1. Record:
 *    ./gradlew :dialogs:roborazzi:recordRoborazziDebug --tests '*Dialog*'
 * 2. Verify:
 *    ./gradlew :dialogs:roborazzi:verifyRoborazziDebug --tests '*Dialog*'
 *
 * See results under "Project" View
 */

/**
 *  Example of Robolectric Screenshot test for different API levels with Roborazzi
 *  This is possible due to Robolectric's annotation @Config(sdk = [30, 31])
 */
@RunWith(RobolectricTestRunner::class)
class DifferentApiLevelsDeleteDialogTest {

    @get:Rule
    val activityScenarioForViewRule =
        RobolectricActivityScenarioForViewRule(
            config = ViewConfigItem(
                locale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            ),
            deviceScreen = DeviceScreen.Phone.PIXEL_5,
            backgroundColor = TRANSPARENT,
        )

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [30, 31])
    @Test
    fun snapDialog() {
        val sdkVersion = Build.VERSION.SDK_INT
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForMeasuredDialog {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = listOf("API $sdkVersion").toTypedArray()
            )
        }

        dialog
            .drawToBitmap()
            .captureRoboImage(
                filePath("DeleteDialog_API_$sdkVersion")
            )
    }
}
