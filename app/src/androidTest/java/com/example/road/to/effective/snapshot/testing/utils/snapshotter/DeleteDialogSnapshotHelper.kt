package com.example.road.to.effective.snapshot.testing.utils.snapshotter

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import com.example.road.to.effective.snapshot.testing.DialogBuilder
import com.example.road.to.effective.snapshot.testing.MainActivity
import com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.DeleteDialogTestItem
import com.karumi.shot.ScreenshotTest
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForView

object DeleteDialogSnapshotHelper : ScreenshotTest {

    fun snapDeleteDialogWithTestRule(testItem: DeleteDialogTestItem) {
        val activity = ActivityScenarioConfigurator.ForActivity()
            .setUiMode(testItem.uiMode)
            .launch(MainActivity::class.java)
            .waitForActivity()

        val dialog = waitForView {
            DialogBuilder.buildDeleteDialog(
                activity,
                onDeleteClicked = { /* no-op*/ },
                bulletTexts = itemArray(testItem.texts)
            )
        }

        compareScreenshot(
            dialog,
            name = testItem.testName,
            widthInPx = testItem.dialogWidth.widthInPx
        )
    }

    fun snapDeleteDialogWithActivityScenario(testItem: DeleteDialogTestItem) {
        val activity =
            ActivityScenarioConfigurator.ForView()
                .setFontSize(testItem.fontScale)
                .setUiMode(testItem.uiMode)
                .launchConfiguredActivity()
                .waitForActivity()

        val dialog = waitForView {
            DialogBuilder.buildDeleteDialog(
                activity,
                onDeleteClicked = {/* no-op*/ },
                bulletTexts = itemArray(testItem.texts)
            )
        }

        compareScreenshot(
            dialog,
            name = testItem.testName,
            widthInPx = testItem.dialogWidth.widthInPx
        )
    }

    private fun itemArray(@StringRes resources: Array<Int>): Array<String> =
        resources.map { InstrumentationRegistry.getInstrumentation().targetContext.getString(it) }
            .toTypedArray()
}