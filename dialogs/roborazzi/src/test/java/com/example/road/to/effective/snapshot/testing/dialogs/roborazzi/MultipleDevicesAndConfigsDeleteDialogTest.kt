package com.example.road.to.effective.snapshot.testing.dialogs.roborazzi

import android.graphics.Color.TRANSPARENT
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.R.*
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForViewRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Tablet.MEDIUM_TABLET
import sergio.sastre.uitesting.robolectric.utils.view.TestDataForView
import sergio.sastre.uitesting.robolectric.utils.view.TestDataForViewCombinator
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.UiMode.DAY
import sergio.sastre.uitesting.utils.common.UiMode.NIGHT
import sergio.sastre.uitesting.utils.utils.drawToBitmap
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

/**
 * Execute the command below to run only ViewHolderTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:roborazzi:recordRoborazziDebug --tests '*MultipleDevicesAndConfigs*'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:roborazzi:verifyRoborazziDebug --tests '*MultipleDevicesAndConfigs*'
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
 *  You can delete it build.gradle:
 *
 *  systemProperty 'robolectric.pixelCopyRenderMode', 'hardware'
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
class MultipleDevicesAndConfigsDeleteDialogTest(
    private val testItem: TestDataForView<DeleteDialogUiState>,
) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun testItemProvider(): Array<TestDataForView<DeleteDialogUiState>> =
            TestDataForViewCombinator(
                uiStates = DeleteDialogUiState.entries.toTypedArray()
            )
                .forDevices(
                    PIXEL_4A,
                    MEDIUM_TABLET,
                )
                .forConfigs(
                    ViewConfigItem(uiMode = DAY),
                    ViewConfigItem(uiMode = NIGHT),
                )
                .combineAll()
    }

    @get:Rule
    val rule = RobolectricActivityScenarioForViewRule(
        config = testItem.config,
        backgroundColor = TRANSPARENT,
        deviceScreen = testItem.device,
    )

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [33])
    @Test
    fun snapDialog() {
        val activity = rule.activity

        val dialog = waitForMeasuredDialog(exactWidthPx = testItem.uiState.dialogWidth.widthInPx) {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts =
                testItem.uiState.bulletTexts
                    .map { activity.getString(it) }
                    .toTypedArray()
            )
        }

        dialog
            .drawToBitmapWithElevation()
            .captureRoboImage(
                filePath("DeleteDialog_${testItem.screenshotId}")
            )
    }
}

enum class DeleteDialogUiState(
    val bulletTexts: List<Int>,
    val dialogWidth: DialogWidth,
) {
    SPACIOUS(
        bulletTexts = listOf(string.shortest),
        dialogWidth = DialogWidth.WIDE,
    ),
    SUFFOCATED(
        bulletTexts = repeatedItem(7, string.largest),
        dialogWidth = DialogWidth.NARROW,
    ),
}
