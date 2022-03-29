package com.example.road.to.effective.snapshot.testing.utils.snapshotter

import android.content.Context
import androidx.annotation.StringRes
import com.example.road.to.effective.snapshot.testing.DialogBuilder
import com.example.road.to.effective.snapshot.testing.testparameterinjector.deletedialog.DeleteDialogTestItem
import com.karumi.shot.ScreenshotTest
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForView

object DeleteDialogSnapshotHelper : ScreenshotTest {

    fun snapDeleteDialog(testItem: DeleteDialogTestItem) {
        val activityScenario =
            ActivityScenarioConfigurator.ForView()
                .setFontSize(testItem.fontScale)
                .setUiMode(testItem.uiMode)
                .launchConfiguredActivity()

        val activity = activityScenario.waitForActivity()

        val dialog = waitForView {
            DialogBuilder.buildDeleteDialog(
                activity,
                onDeleteClicked = {/* no-op*/ },
                bulletTexts = itemArray(activity, testItem.texts)
            )
        }

        compareScreenshot(
            dialog,
            name = testItem.testName,
            widthInPx = testItem.dialogWidth.widthInPx
        )

        activityScenario.close()
    }

    private fun itemArray(context: Context, @StringRes resources: Array<Int>): Array<String> =
        resources.map { context.getString(it) }
            .toTypedArray()
}
