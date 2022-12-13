package com.example.road.to.effective.snapshot.testing.dialogs.shot.parameterized

import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.shot.compareDialogScreenshot
import com.example.road.to.effective.snapshot.testing.dialogs.shot.waitForMeasuredDialog
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule

/**
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 *
 * On the other hand, TestParameterInjector requires API 24+ to run with instrumented tests.
 * It throws java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder in lower APIs.
 * Parameterized Runner is compatible with instrumented test of any API level
 */
@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class DeleteDialogTestParameterHappyPathTest(
    @TestParameter val testItem: HappyPathTestItem,
) : ScreenshotTest {

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(config = testItem.deleteItem.viewConfig)

    @HappyPath
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForMeasuredDialog {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {/* no-op*/ },
                bulletTexts = itemArray(activity, testItem.deleteItem.bulletTexts)
            )
        }

        compareDialogScreenshot(
            dialog = dialog,
            heightInPx = dialog.window?.decorView?.measuredHeight,
            widthInPx = testItem.deleteItem.dialogWidth.widthInPx,
            name = "DeleteDialog_${testItem.name}_TestParameter",
        )
    }
}

@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class DeleteDialogTestParameterUnhappyPathTest(
    @TestParameter val testItem: UnhappyPathTestItem,
) : ScreenshotTest {

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(config = testItem.deleteItem.viewConfig)

    @UnhappyPath
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForMeasuredDialog {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {/* no-op*/ },
                bulletTexts = itemArray(activity, testItem.deleteItem.bulletTexts)
            )
        }

        compareDialogScreenshot(
            dialog = dialog,
            heightInPx = dialog.window?.decorView?.measuredHeight,
            widthInPx = testItem.deleteItem.dialogWidth.widthInPx,
            name = "DeleteDialog_${testItem.name}_TestParameter",
        )
    }
}
