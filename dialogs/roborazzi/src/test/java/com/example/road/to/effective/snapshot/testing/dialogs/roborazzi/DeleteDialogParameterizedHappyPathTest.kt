package com.example.road.to.effective.snapshot.testing.dialogs.roborazzi

import android.graphics.Color
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForViewRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_5
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog
import java.io.File

/**
 * Execute the command below to run only DialogTests
 * 1. Record:
 *    ./gradlew :dialogs:roborazzi:recordRoborazziDebug --tests '*Dialog*'
 * 2. Verify:
 *    ./gradlew :dialogs:roborazzi:verifyRoborazziDebug --tests '*Dialog*'
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
@RunWith(ParameterizedRobolectricTestRunner::class)
class DeleteDialogParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {

    private val deleteItem = testItem.deleteItem

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> =
            HappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val activityScenarioForViewRule =
        RobolectricActivityScenarioForViewRule(
            config = deleteItem.viewConfig,
            backgroundColor = Color.TRANSPARENT,
            deviceScreen = PIXEL_5,
        )

    @GraphicsMode(NATIVE)
    @Config(sdk = [33])
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForMeasuredDialog(exactWidthPx = deleteItem.dialogWidth.widthInPx) {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(activity, deleteItem.bulletTexts)
            )
        }

        dialog
            .drawToBitmapWithElevation()
            .captureRoboImage(
                filePath("DeleteDialog_${testItem.name}_Parameterized")
            )
    }
}

@RunWith(ParameterizedRobolectricTestRunner::class)
class DeleteDialogParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    private val deleteItem = testItem.deleteItem

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> =
            UnhappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val activityScenarioForViewRule =
        RobolectricActivityScenarioForViewRule(
            config = deleteItem.viewConfig,
            backgroundColor = Color.TRANSPARENT,
            deviceScreen = PIXEL_5,
        )

    @GraphicsMode(NATIVE)
    @Config(sdk = [33])
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForMeasuredDialog(exactWidthPx = deleteItem.dialogWidth.widthInPx) {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(activity, deleteItem.bulletTexts)
            )
        }

        dialog
            .drawToBitmapWithElevation()
            .captureRoboImage(
                filePath("DeleteDialog_${testItem.name}_Parameterized")
            )
    }
}

fun filePath(name: String): String {
    val path = System.getProperty("user.dir")
    val file = File("$path/src/test", "$name.png")
    return file.path
}
