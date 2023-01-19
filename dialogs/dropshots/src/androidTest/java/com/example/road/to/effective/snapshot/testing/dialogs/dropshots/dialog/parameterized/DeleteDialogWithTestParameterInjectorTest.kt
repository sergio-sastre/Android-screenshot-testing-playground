package com.example.road.to.effective.snapshot.testing.dialogs.dropshots.dialog.parameterized

import android.graphics.Color.TRANSPARENT
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

/**
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 *
 * On the other hand, TestParameterInjector requires API 24+ to run with instrumented tests.
 * It throws java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder in lower APIs.
 * Parameterized Runner is compatible with instrumented test of any API level
 */
@RunWith(TestParameterInjector::class)
class DeleteDialogTestParameterHappyPathTest(
    @TestParameter val testItem: HappyPathTestItem,
) {

    private val deleteItem = testItem.deleteItem

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(
            config = testItem.deleteItem.viewConfig,
            backgroundColor = TRANSPARENT,
        )

    @HappyPath
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForMeasuredDialog(exactWidthPx = deleteItem.dialogWidth.widthInPx) {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(activity, testItem.deleteItem.bulletTexts)
            )
        }

        dropshots.assertSnapshot(
            view = dialog.window!!.decorView,
            name = "DeleteDialog_${testItem.name}_TestParameter",
        )
    }
}

@RunWith(TestParameterInjector::class)
class DeleteDialogTestParameterUnhappyPathTest(
    @TestParameter val testItem: UnhappyPathTestItem,
) {

    private val deleteItem = testItem.deleteItem

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(
            config = testItem.deleteItem.viewConfig,
            backgroundColor = TRANSPARENT,
        )

    @UnhappyPath
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForMeasuredDialog(exactWidthPx = deleteItem.dialogWidth.widthInPx) {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(activity, testItem.deleteItem.bulletTexts)
            )
        }

        dropshots.assertSnapshot(
            view = dialog.window!!.decorView,
            name = "DeleteDialog_${testItem.name}_TestParameter",
        )
    }
}
