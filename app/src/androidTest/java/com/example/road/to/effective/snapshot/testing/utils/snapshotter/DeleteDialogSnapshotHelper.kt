package com.example.road.to.effective.snapshot.testing.utils.snapshotter

import androidx.annotation.StringRes
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.example.road.to.effective.snapshot.testing.DialogBuilder
import com.example.road.to.effective.snapshot.testing.MainActivity
import com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.DeleteDialogTestItem
import com.example.road.to.effective.snapshot.testing.utils.waitForActivity
import com.example.road.to.effective.snapshot.testing.utils.waitForView
import com.karumi.shot.ScreenshotTest
import sergio.sastre.fontsize.activityscenario.FontSizeActivityScenario

object DeleteDialogSnapshotHelper : ScreenshotTest {

    fun snapDeleteDialogWithTestRule(testItem: DeleteDialogTestItem) {
        val activity = ActivityScenario.launch(MainActivity::class.java)
            .waitForActivity(testItem.theme.themId)

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
        val activity = FontSizeActivityScenario.launchWith(testItem.fontScale)
            .waitForActivity(testItem.theme.themId)

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