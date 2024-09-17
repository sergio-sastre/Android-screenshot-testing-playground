package com.example.road.to.effective.snapshot.testing.dialogs.roborazzi.bitmap


import android.app.Dialog
import android.graphics.Color
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.R
import com.example.road.to.effective.snapshot.testing.dialogs.roborazzi.filePath
import com.example.road.to.effective.snapshot.testing.dialogs.roborazzi.itemArray
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForViewRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen
import sergio.sastre.uitesting.utils.utils.drawToBitmap
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

/**
 * Execute the command below to run only BitmapTests
 * 1. Record:
 *    ./gradlew :dialogs:roborazzi:recordRoborazziDebug --tests '*Bitmap*'
 * 2. Verify:
 *    ./gradlew :dialogs:roborazzi:verifyRoborazziDebug --tests '*Bitmap*'
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
class DeleteDialogBitmapTest {

    @get:Rule
    val activityScenarioForViewRule =
        RobolectricActivityScenarioForViewRule(
            backgroundColor = Color.TRANSPARENT,
            deviceScreen = DeviceScreen.Phone.PIXEL_5,
        )

    private fun createDialog(): Dialog {
        val activity = activityScenarioForViewRule.activity

        return waitForMeasuredDialog {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(activity, listOf(R.string.shortest))
            )
        }
    }

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [33])
    @Test
    fun snapDialogWithPixelCopy() {
        createDialog()
            .drawToBitmapWithElevation()
            .captureRoboImage(
                filePath("DeleteDialog_BitmapWithElevation")
            )
    }

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [33])
    @Test
    fun snapDialogWithCanvas() {
        createDialog()
            .drawToBitmap()
            .captureRoboImage(
                filePath("DeleteDialog_BitmapWithoutElevation")
            )
    }
}