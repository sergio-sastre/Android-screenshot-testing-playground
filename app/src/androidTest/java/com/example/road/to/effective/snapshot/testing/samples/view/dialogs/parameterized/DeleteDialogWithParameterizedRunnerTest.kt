package com.example.road.to.effective.snapshot.testing.samples.view.dialogs.parameterized

import com.example.road.to.effective.snapshot.testing.DialogBuilder
import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.compareDialogScreenshot
import com.example.road.to.effective.snapshot.testing.utils.waitForDialogView
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule

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

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(config = testItem.deleteItem.viewConfig)

    @HappyPath
    @Test
    fun snapDialogHappyPath() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForDialogView {
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
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class DeleteDialogParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(config = testItem.deleteItem.viewConfig)

    @UnhappyPath
    @Test
    fun snapDialogUnhappyPath() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForDialogView {
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
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}
