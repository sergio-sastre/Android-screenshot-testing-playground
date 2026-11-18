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
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.testrules.accessibility.outlinetext.OutlineText
import sergio.sastre.uitesting.utils.testrules.accessibility.outlinetext.OutlineTextTestRule
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
class DeleteDialogOutlineTextTest : ScreenshotTest {

    private val deleteItem = DeleteDialogTestItem(
        bulletTexts = listOf(R.string.largest, R.string.middle, R.string.shortest),
        dialogWidth = DialogWidth.NORMAL,
    )

    @get:Rule(order = 1)
    val outlineTextTestRule =
        OutlineTextTestRule(OutlineText.ENABLED)

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
            name = "DeleteDialog_OutlineText",
        )
    }
}
