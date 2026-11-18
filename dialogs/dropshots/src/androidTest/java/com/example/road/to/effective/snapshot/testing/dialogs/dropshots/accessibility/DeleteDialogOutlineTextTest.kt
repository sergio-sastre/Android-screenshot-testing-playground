package com.example.road.to.effective.snapshot.testing.dialogs.dropshots.accessibility

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.R
import com.example.road.to.effective.snapshot.testing.dialogs.dropshots.dialog.parameterized.DeleteDialogTestItem
import com.example.road.to.effective.snapshot.testing.dialogs.dropshots.dialog.parameterized.DialogWidth
import com.example.road.to.effective.snapshot.testing.dialogs.dropshots.dialog.parameterized.itemArray
import com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator.PortraitSnapshotConfiguredActivity
import sergio.sastre.uitesting.utils.activityscenario.activityScenarioForActivityRule
import sergio.sastre.uitesting.utils.testrules.accessibility.outlinetext.OutlineText
import sergio.sastre.uitesting.utils.testrules.accessibility.outlinetext.OutlineTextTestRule
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :dialogs:dropshots:recordScreenshots -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 * 2. Verify:
 *    ./gradlew :dialogs:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class DeleteDialogOutlineTextTest {

    val deleteItem = DeleteDialogTestItem(
        bulletTexts = listOf(R.string.largest, R.string.middle, R.string.shortest),
        dialogWidth = DialogWidth.NORMAL,
    )

    @get:Rule(order = 1)
    val outlineTextTestRule =
        OutlineTextTestRule(OutlineText.ENABLED)

    @get:Rule(order = 2)
    val activityScenarioForActivity =
        activityScenarioForActivityRule<PortraitSnapshotConfiguredActivity>()

    @get:Rule(order = 3)
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

    @AccessibilityTest
    @Test
    fun snapDialog() {
        val activity = activityScenarioForActivity.activity

        val dialog = waitForMeasuredDialog {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(activity, deleteItem.bulletTexts)
            )
        }

        dropshots.assertSnapshot(
            bitmap = dialog.window!!.decorView.drawToBitmapWithElevation(),
            name = "DeleteDialog_OutlineText",
            filePath = "accessibility",
        )
    }
}
