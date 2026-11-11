package com.example.road.to.effective.snapshot.testing.dialogs.shot.accessibility

import android.graphics.Color.TRANSPARENT
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.R
import com.example.road.to.effective.snapshot.testing.dialogs.shot.parameterized.DeleteDialogTestItem
import com.example.road.to.effective.snapshot.testing.dialogs.shot.parameterized.DialogWidth
import com.example.road.to.effective.snapshot.testing.dialogs.shot.parameterized.itemArray
import com.example.road.to.effective.snapshot.testing.dialogs.shot.utils.compareDialogScreenshot
import com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.testrules.accessibility.colorcontrast.ColorContrast
import sergio.sastre.uitesting.utils.testrules.accessibility.colorcontrast.ColorContrastTestRule
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :dialogs:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest -Precord
 * 2. Verify:
 *    ./gradlew :dialogs:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
@RunWith(Parameterized::class)
class DeleteDialogColorContrastTest(
    private val colorContrastOption: ColorContrast,
) : ScreenshotTest {

    private val deleteItem = DeleteDialogTestItem(
        bulletTexts = listOf(R.string.largest, R.string.middle, R.string.shortest),
        dialogWidth = DialogWidth.NORMAL,
    )

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<ColorContrast> =
            ColorContrast.entries.toTypedArray()
    }

    @get:Rule(order = 1)
    val colorContrastTestRule =
        ColorContrastTestRule(colorContrastOption)

    @get:Rule(order = 2)
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(
            config = deleteItem.viewConfig,
            backgroundColor = TRANSPARENT,
        )

    @AccessibilityTest
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForMeasuredDialog(exactWidthPx = deleteItem.dialogWidth.widthInPx) {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {/* no-op*/ },
                bulletTexts = itemArray(activity, deleteItem.bulletTexts)
            )
        }

        compareDialogScreenshot(
            dialog = dialog,
            name = "DeleteDialog_${colorContrastOption.name}_Parameterized",
        )
    }
}
