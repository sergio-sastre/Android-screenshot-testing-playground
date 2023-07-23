package com.example.road.to.effective.snapshot.testing.dialogs.shot.parameterized

import android.graphics.Color.TRANSPARENT
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.shot.utils.compareDialogScreenshot
import com.example.road.to.effective.snapshot.testing.testannotations.DialogTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

/**
 * Execute the command below to run only DialogTests
 * 1. Record:
 *    ./gradlew :dialogs:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.DialogTest -Precord
 * 2. Verify:
 *    ./gradlew :dialogs:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.DialogTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 *
 * On the other hand, TestParameterInjector requires API 24+ to run with instrumented tests,
 * java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder error in lower APIs,
 * whereas Parameterized Runner is compatible with instrumented test of any API level
 */
@RunWith(Parameterized::class)
class DeleteDialogParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) : ScreenshotTest {

    private val deleteItem = testItem.deleteItem

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(
            config = deleteItem.viewConfig,
            backgroundColor = TRANSPARENT,
        )

    @HappyPath
    @DialogTest
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
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class DeleteDialogParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) : ScreenshotTest {

    private val deleteItem = testItem.deleteItem

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(
            config = deleteItem.viewConfig,
            backgroundColor = TRANSPARENT,
        )

    @UnhappyPath
    @DialogTest
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog =
            waitForMeasuredDialog(exactWidthPx = deleteItem.dialogWidth.widthInPx) {
                DialogBuilder.buildDeleteDialog(
                    ctx = activity,
                    onDeleteClicked = {/* no-op*/ },
                    bulletTexts = itemArray(activity, deleteItem.bulletTexts)
                )
            }

        compareDialogScreenshot(
            dialog = dialog,
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}

